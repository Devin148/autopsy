/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2012 Basis Technology Corp.
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
package org.sleuthkit.autopsy.directorytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.sleuthkit.autopsy.datamodel.Tags;


public class TagTreeModel implements TreeModel {
    List<String> tagNames;
    Map<String, List<String>> tagPaths;
    
    TagTreeModel() {
        tagNames = Tags.getTagNames();
        tagPaths = new HashMap<String, List<String>>();
        
        for (String path : tagNames) {
            String parent = path.substring(0, path.indexOf("/"));
            List<String> rest = new ArrayList<String>(Arrays.asList(path.substring(path.indexOf("/")).split("/")));
            tagPaths.put(parent, rest);
        }
    }

    @Override
    public Object getRoot() {
        return "Tags";
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent.toString().equals("Tags")) {
            return tagNames.get(index);
        } else {
            return tagPaths.get(parent.toString()).get(index);
        }
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent.toString().equals("Tags")) {
            return tagNames.size();
        } else {
            return 1;
        }
    }

    @Override
    public boolean isLeaf(Object node) {
        return !node.toString().equals("Tags");
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return tagNames.indexOf(child.toString());
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }

}