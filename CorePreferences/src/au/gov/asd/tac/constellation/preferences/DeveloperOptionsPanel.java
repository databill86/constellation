/*
 * Copyright 2010-2019 Australian Signals Directorate
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
package au.gov.asd.tac.constellation.preferences;

import java.util.ArrayList;
import java.util.Collection;
import org.netbeans.api.autoupdate.OperationContainer;
import org.netbeans.api.autoupdate.OperationException;
import org.netbeans.api.autoupdate.OperationSupport;
import org.netbeans.api.autoupdate.UpdateElement;
import org.netbeans.api.autoupdate.UpdateManager;
import org.netbeans.api.autoupdate.UpdateUnit;

/**
 *
 * @author sirius
 */
public class DeveloperOptionsPanel extends javax.swing.JPanel {

    private final DeveloperOptionsPanelController controller;

    public DeveloperOptionsPanel(final DeveloperOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
    }

    public boolean getGcOnOpen() {
        return gcOnOpenCheckbox.isSelected();
    }

    public void setGcOnOpen(final boolean gcOnOpen) {
        gcOnOpenCheckbox.setSelected(gcOnOpen);
    }

    public boolean getGcOnClose() {
        return gcOnCloseCheckbox.isSelected();
    }

    public void setGcOnClose(final boolean gcOnClose) {
        gcOnCloseCheckbox.setSelected(gcOnClose);
    }

    public boolean getDebugGl() {
        return debugGlCheckbox.isSelected();
    }

    public void setDebugGl(final boolean debugGl) {
        debugGlCheckbox.setSelected(debugGl);
    }

    public boolean getPrintGl() {
        return printGlCheckbox.isSelected();
    }

    public void setPrintGl(final boolean printGl) {
        printGlCheckbox.setSelected(printGl);
    }

    public boolean getDisplayFps() {
        return displayFpsCheckbox.isSelected();
    }

    public void setDisplayFps(final boolean displayFps) {
        displayFpsCheckbox.setSelected(displayFps);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterResetButton = new javax.swing.JButton();
        memoryPanel = new javax.swing.JPanel();
        gcOnOpenCheckbox = new javax.swing.JCheckBox();
        gcOnCloseCheckbox = new javax.swing.JCheckBox();
        glPanel = new javax.swing.JPanel();
        debugGlCheckbox = new javax.swing.JCheckBox();
        printGlCheckbox = new javax.swing.JCheckBox();
        displayFpsCheckbox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(masterResetButton, org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.masterResetButton.text")); // NOI18N
        masterResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterResetButtonActionPerformed(evt);
            }
        });

        memoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.memoryPanel.border.title"))); // NOI18N
        memoryPanel.setName(""); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(gcOnOpenCheckbox, org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.gcOnOpenCheckbox.text")); // NOI18N
        gcOnOpenCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcOnOpenCheckboxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(gcOnCloseCheckbox, org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.gcOnCloseCheckbox.text")); // NOI18N
        gcOnCloseCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcOnCloseCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout memoryPanelLayout = new javax.swing.GroupLayout(memoryPanel);
        memoryPanel.setLayout(memoryPanelLayout);
        memoryPanelLayout.setHorizontalGroup(
            memoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memoryPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(memoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gcOnCloseCheckbox)
                    .addComponent(gcOnOpenCheckbox))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        memoryPanelLayout.setVerticalGroup(
            memoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gcOnOpenCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gcOnCloseCheckbox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        glPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.glPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(debugGlCheckbox, org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.debugGlCheckbox.text")); // NOI18N
        debugGlCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugGlCheckboxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(printGlCheckbox, org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.printGlCheckbox.text")); // NOI18N
        printGlCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printGlCheckboxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(displayFpsCheckbox, org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.displayFpsCheckbox.text")); // NOI18N
        displayFpsCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayFpsCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glPanelLayout = new javax.swing.GroupLayout(glPanel);
        glPanel.setLayout(glPanelLayout);
        glPanelLayout.setHorizontalGroup(
            glPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(glPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(debugGlCheckbox)
                    .addComponent(printGlCheckbox)
                    .addComponent(displayFpsCheckbox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        glPanelLayout.setVerticalGroup(
            glPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glPanelLayout.createSequentialGroup()
                .addComponent(debugGlCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printGlCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayFpsCheckbox))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(glPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(masterResetButton))
                    .addComponent(memoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(memoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(glPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(masterResetButton)
                .addContainerGap())
        );

        glPanel.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DeveloperOptionsPanel.class, "DeveloperOptionsPanel.glPanel.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    private void masterResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterResetButtonActionPerformed

        Collection<UpdateElement> toUninstall = new ArrayList<>();

        for (UpdateUnit unit : UpdateManager.getDefault().getUpdateUnits()) {
            String codeName = unit.getCodeName();
            if (!codeName.equals("au.gov.asd.tac.constellation.autoupdate")
                    && !codeName.startsWith("org.netbeans.")
                    && !codeName.startsWith("org.openide.")
                    && !codeName.startsWith("org.jdesktop.")) {
                UpdateElement element = unit.getInstalled();
                if (element != null) {
                    toUninstall.add(element);
                }
            }
        }

        OperationContainer<OperationSupport> operationContainer = OperationContainer.createForUninstall();
        operationContainer.add(toUninstall);

        OperationSupport operationSupport = operationContainer.getSupport();
        try {
            operationSupport.doOperation(null);
        } catch (OperationException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_masterResetButtonActionPerformed

    private void gcOnOpenCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcOnOpenCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gcOnOpenCheckboxActionPerformed

    private void gcOnCloseCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcOnCloseCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gcOnCloseCheckboxActionPerformed

    private void displayFpsCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayFpsCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displayFpsCheckboxActionPerformed

    private void printGlCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printGlCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printGlCheckboxActionPerformed

    private void debugGlCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugGlCheckboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debugGlCheckboxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox debugGlCheckbox;
    private javax.swing.JCheckBox displayFpsCheckbox;
    private javax.swing.JCheckBox gcOnCloseCheckbox;
    private javax.swing.JCheckBox gcOnOpenCheckbox;
    private javax.swing.JPanel glPanel;
    private javax.swing.JButton masterResetButton;
    private javax.swing.JPanel memoryPanel;
    private javax.swing.JCheckBox printGlCheckbox;
    // End of variables declaration//GEN-END:variables
}
