/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.keywordsearch;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sleuthkit.autopsy.keywordsearch.Ingester.IngesterException;
import org.sleuthkit.datamodel.AbstractFile;





/**
 * Takes an AbstractFile, extract strings, converts into chunks (associated with the original
 * source file) up to 1MB then and indexes chunks as text with Solr
 */
class AbstractFileStringExtract implements AbstractFileExtract {

    private KeywordSearchIngestService service;
    private Ingester ingester;
    private int numChunks;
    private static final Logger logger = Logger.getLogger(AbstractFileStringExtract.class.getName());
    static final long MAX_STRING_CHUNK_SIZE = 1 * 1024 * 1024L;
    private AbstractFile aFile;
    //single static buffer for all extractions.  Safe, indexing can only happen in one thread
    private static final byte[] STRING_CHUNK_BUF = new byte[(int) MAX_STRING_CHUNK_SIZE];
    private static final int BOM_LEN = 3;

    static {
        //prepend UTF-8 BOM to start of the buffer
        STRING_CHUNK_BUF[0] = (byte) 0xEF;
        STRING_CHUNK_BUF[1] = (byte) 0xBB;
        STRING_CHUNK_BUF[2] = (byte) 0xBF;
    }

    public AbstractFileStringExtract(AbstractFile aFile) {
        this.aFile = aFile;
        numChunks = 0; //unknown until indexing is done
        this.service = KeywordSearchIngestService.getDefault();
        Server solrServer = KeywordSearch.getServer();
        ingester = solrServer.getIngester();
    }

    @Override
    public int getNumChunks() {
        return this.numChunks;
    }

    @Override
    public AbstractFile getSourceFile() {
        return aFile;
    }

    @Override
    public boolean index() throws IngesterException {
        boolean success = false;

        //construct stream that extracts text as we read it
        final InputStream stringStream = new AbstractFileStringStream(aFile, ByteContentStream.Encoding.UTF8);

        try {
            success = true;
            //break input stream into chunks 
            
            long readSize = 0;
            while ((readSize = stringStream.read(STRING_CHUNK_BUF, BOM_LEN, (int) MAX_STRING_CHUNK_SIZE - BOM_LEN)) != -1) {
                //FileOutputStream debug = new FileOutputStream("c:\\temp\\" + sourceFile.getName() + Integer.toString(this.numChunks+1));
                //debug.write(STRING_CHUNK_BUF, 0, (int)readSize);

                AbstractFileChunk chunk = new AbstractFileChunk(this, this.numChunks + 1);

                try {
                    chunk.index(ingester, STRING_CHUNK_BUF, readSize + BOM_LEN, ByteContentStream.Encoding.UTF8);
                    ++this.numChunks;
                } catch (IngesterException ingEx) {
                    success = false;
                    logger.log(Level.WARNING, "Ingester had a problem with extracted strings from file '" + aFile.getName() + "' (id: " + aFile.getId() + ").", ingEx);
                    throw ingEx; //need to rethrow/return to signal error and move on
                }

                //check if need invoke commit/search between chunks
                //not to delay commit if timer has gone off
                service.checkRunCommitSearch();

                //debug.close();    
            }


            //after all chunks, ingest the parent file without content itself, and store numChunks
            ingester.ingest(this);

        } catch (IOException ex) {
            logger.log(Level.WARNING, "Unable to read input stream to divide and send to Solr, file: " + aFile.getName(), ex);
            success = false;
        } finally {
            try {
                stringStream.close();
            } catch (IOException ex) {
                logger.log(Level.WARNING, "Error closing input stream stream, file: " + aFile.getName(), ex);
            }
        }


        return success;
    }
    

}

