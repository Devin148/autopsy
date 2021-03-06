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

package org.sleuthkit.autopsy.corecomponentinterfaces;

import java.beans.PropertyChangeListener;
import org.openide.nodes.Node;
import org.openide.windows.TopComponent;

/**
 * The interface for the "bottom right component" window.
 *
 * @author jantonius
 */
public interface DataContent extends PropertyChangeListener {

    /**
     * Sets the "selected" node in this class
     * @param selectedNode node to use
     */
    public void setNode(Node selectedNode);

    /**
     * Get the TopComponent that is used when displaying this DataContent
     * @return TopComponent for this DataContent
     */
    public TopComponent getTopComponent();
}
