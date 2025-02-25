/*
 *  ----------------------------------------------------------------------------
 *  Copyright (C) 2021      European Space Agency
 *                          European Space Operations Centre
 *                          Darmstadt
 *                          Germany
 *  ----------------------------------------------------------------------------
 *  System                : ESA NanoSat MO Framework
 *  ----------------------------------------------------------------------------
 *  Licensed under European Space Agency Public License (ESA-PL) Weak Copyleft – v2.4
 *  You may not use this file except in compliance with the License.
 * 
 *  Except as expressly set forth in this License, the Software is provided to
 *  You on an "as is" basis and without warranties of any kind, including without
 *  limitation merchantability, fitness for a particular purpose, absence of
 *  defects or errors, accuracy or non-infringement of intellectual property rights.
 *  
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 *  ----------------------------------------------------------------------------
 */
package opssat.simulator.gui;

import javax.swing.JTextArea;

/**
 *
 * @author cezar
 */
public class WholeTextAreaObject {
    JTextArea textArea;
    Boolean updateValues;

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public boolean isUpdateValues() {
        return updateValues;
    }

    public void setUpdateValues(boolean updateValues) {
        this.updateValues = updateValues;
    }

    public WholeTextAreaObject(JTextArea textArea, Boolean updateValues) {
        this.textArea = textArea;
        this.updateValues = updateValues;
    }
    
    
}
