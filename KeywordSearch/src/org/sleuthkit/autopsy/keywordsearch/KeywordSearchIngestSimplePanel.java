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

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import org.sleuthkit.autopsy.coreutils.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import org.sleuthkit.autopsy.coreutils.ModuleSettings;
import org.sleuthkit.autopsy.coreutils.StringExtract.StringExtractUnicodeTable.SCRIPT;

/**
 * Simple ingest config panel
 */
public class KeywordSearchIngestSimplePanel extends javax.swing.JPanel {
    
    private final static Logger logger = Logger.getLogger(KeywordSearchIngestSimplePanel.class.getName());
    public static final String PROP_OPTIONS = "Keyword Search_Options";
    private KeywordTableModel tableModel;
    private List<KeywordSearchList> lists;

    /** Creates new form KeywordSearchIngestSimplePanel */
    public KeywordSearchIngestSimplePanel() {
        tableModel = new KeywordTableModel();
        lists = new ArrayList<KeywordSearchList>();
        reloadLists();
        initComponents();
        customizeComponents();
    }
    
    private void customizeComponents() {
        listsTable.setModel(tableModel);
        
        listsTable.setTableHeader(null);
        listsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //customize column witdhs
        final int width = listsScrollPane.getPreferredSize().width;
        listsTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumn column = null;
        for (int i = 0; i < listsTable.getColumnCount(); i++) {
            column = listsTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(((int) (width * 0.07)));
            } else {
                column.setPreferredWidth(((int) (width * 0.92)));
            }
        }
        
        reloadLangs();
        reloadEncodings();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listsScrollPane = new javax.swing.JScrollPane();
        listsTable = new javax.swing.JTable();
        titleLabel = new javax.swing.JLabel();
        languagesLabel = new javax.swing.JLabel();
        languagesValLabel = new javax.swing.JLabel();
        encodingsLabel = new javax.swing.JLabel();
        keywordSearchEncodings = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(300, 170));

        listsScrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        listsScrollPane.setPreferredSize(new java.awt.Dimension(300, 100));

        listsTable.setBackground(new java.awt.Color(240, 240, 240));
        listsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        listsTable.setShowHorizontalLines(false);
        listsTable.setShowVerticalLines(false);
        listsScrollPane.setViewportView(listsTable);

        titleLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchIngestSimplePanel.class, "KeywordSearchIngestSimplePanel.titleLabel.text")); // NOI18N

        languagesLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchIngestSimplePanel.class, "KeywordSearchIngestSimplePanel.languagesLabel.text")); // NOI18N
        languagesLabel.setToolTipText(org.openide.util.NbBundle.getMessage(KeywordSearchIngestSimplePanel.class, "KeywordSearchIngestSimplePanel.languagesLabel.toolTipText")); // NOI18N

        languagesValLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchIngestSimplePanel.class, "KeywordSearchIngestSimplePanel.languagesValLabel.text")); // NOI18N
        languagesValLabel.setToolTipText(org.openide.util.NbBundle.getMessage(KeywordSearchIngestSimplePanel.class, "KeywordSearchIngestSimplePanel.languagesValLabel.toolTipText")); // NOI18N

        encodingsLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchIngestSimplePanel.class, "KeywordSearchIngestSimplePanel.encodingsLabel.text")); // NOI18N

        keywordSearchEncodings.setText(org.openide.util.NbBundle.getMessage(KeywordSearchIngestSimplePanel.class, "KeywordSearchIngestSimplePanel.keywordSearchEncodings.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(listsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(languagesValLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(languagesLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(encodingsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(keywordSearchEncodings)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(languagesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(languagesValLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encodingsLabel)
                    .addComponent(keywordSearchEncodings))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel encodingsLabel;
    private javax.swing.JLabel keywordSearchEncodings;
    private javax.swing.JLabel languagesLabel;
    private javax.swing.JLabel languagesValLabel;
    private javax.swing.JScrollPane listsScrollPane;
    private javax.swing.JTable listsTable;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    private void reloadLangs() {
        //TODO multiple
        List<SCRIPT> scripts = KeywordSearchSettings.getStringExtractScripts();
        StringBuilder langs = new StringBuilder();
        langs.append("<html>");
        for(int i=0; i<scripts.size(); i++) {
            langs.append(scripts.get(i).toString());
            if(i+1 < scripts.size()) {
                langs.append(", ");
            }
        }
        langs.append("</html>");
        String langsS = langs.toString();
        this.languagesValLabel.setText(langsS);
        this.languagesValLabel.setToolTipText(langsS);
    }
    
    private void reloadEncodings() {
        String utf8 = KeywordSearchSettings.getStringExtractOption(AbstractFileExtract.ExtractOptions.EXTRACT_UTF8.toString());
        String utf16 = KeywordSearchSettings.getStringExtractOption(AbstractFileExtract.ExtractOptions.EXTRACT_UTF16.toString());
        ArrayList<String> encodingsList = new ArrayList<String>();
        if(utf8==null || Boolean.parseBoolean(utf8)) {
            encodingsList.add("UTF8");
        }
        if(utf16==null || Boolean.parseBoolean(utf16)) {
            encodingsList.add("UTF16");
        }
        String encodings = encodingsList.toString();
        encodings = encodings.substring(1, encodings.length()-1);
        keywordSearchEncodings.setText(encodings);
    }
    
    private void reloadLists() {
        lists.clear();
        lists.addAll(KeywordSearchListsXML.getCurrent().getListsL());
    }

    private class KeywordTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return KeywordSearchIngestSimplePanel.this.lists.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            KeywordSearchList list = KeywordSearchIngestSimplePanel.this.lists.get(rowIndex);
            if(columnIndex == 0) {
                return list.getUseForIngest();
            } else {
                return list.getName();
            }
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            
            KeywordSearchList list = KeywordSearchIngestSimplePanel.this.lists.get(rowIndex);
            if(columnIndex == 0){
                KeywordSearchListsXML loader = KeywordSearchListsXML.getCurrent();
                loader.addList(list.getName(), list.getKeywords(), (Boolean) aValue, false);
                reloadLists();
            }
        }
        
        @Override
        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
        
    }
}
