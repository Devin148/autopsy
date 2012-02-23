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
package org.sleuthkit.autopsy.ingest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;

/**
 * the main layered pane container for messages table (IngestMessagePanel)
 * and details view (IngestMessageDetailsPanel)
 */
public class IngestMessageMainPanel extends javax.swing.JPanel {

    private IngestMessagePanel messagePanel;
    private IngestMessageDetailsPanel detailsPanel;
    private Logger logger = Logger.getLogger(IngestMessageMainPanel.class.getName());

    /** Creates new form IngestMessageMainPanel */
    public IngestMessageMainPanel() {
        initComponents();
        customizeComponents();
    }

    private void customizeComponents() {
        messagePanel = new IngestMessagePanel(this);
        detailsPanel = new IngestMessageDetailsPanel(this);

        //we need to handle resizing ourselves due to absence of layout manager
        //in layered layout
        this.addComponentListener(new ComponentListener() {

            @Override
            public void componentHidden(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
                
            }

            @Override
            public void componentResized(ComponentEvent e) {
                setSize();
            }
        });

        layeredPane.add(messagePanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(detailsPanel, JLayeredPane.DEFAULT_LAYER);
        
        //TODO handle the initial size better so it resizes to current tc size
        //(somehow the initial tc size is not set correctly)
        //setBackground(Color.red);
        //layeredPane.setBackground(Color.GREEN);
        //messagePanel.setBackground(Color.blue);
        Dimension dim = getPreferredSize();
        messagePanel.setSize(dim);
        detailsPanel.setSize(dim);

        //messagePanel.setBounds(0, 0, dim.width, dim.height);
       // detailsPanel.setBounds(0, 0, dim.width, dim.height);
  
        this.setOpaque(true);
    }

    private void setSize() {
        //we need to handle resizing ourselves due to absence of layout manager
        //in layered layout
        Dimension dim = getSize();
        messagePanel.setPreferredSize(dim);
        detailsPanel.setPreferredSize(dim);

        messagePanel.setBounds(0, 0, dim.width, dim.height);
        detailsPanel.setBounds(0, 0, dim.width, dim.height);

    }

    IngestMessagePanel getMessagePanel() {
        return messagePanel;
    }

    IngestMessageDetailsPanel getDetailsPanel() {
        return detailsPanel;
    }

    void showMessages() {
        layeredPane.setLayer(detailsPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(messagePanel, JLayeredPane.PALETTE_LAYER);
    }

    void showDetails(int rowNumber) {
        detailsPanel.showDetails(rowNumber);

        layeredPane.setLayer(detailsPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.setLayer(messagePanel, JLayeredPane.DEFAULT_LAYER);

    }

    public void addMessage(IngestMessage ingestMessage) {
        messagePanel.addMessage(ingestMessage);
    }

    public void clearMessages() {
        messagePanel.clearMessages();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPane = new javax.swing.JLayeredPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layeredPane;
    // End of variables declaration//GEN-END:variables
}