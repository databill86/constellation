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
package au.gov.asd.tac.constellation.functionality;

/**
 *
 * @author algol
 */
public class DimensionsPanel extends javax.swing.JPanel {

    public DimensionsPanel() {
        initComponents();
    }

    public int getDimensions() {
        return d2rb.isSelected() ? 2 : 3;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dimensionbg = new javax.swing.ButtonGroup();
        d2rb = new javax.swing.JRadioButton();
        d3rb = new javax.swing.JRadioButton();

        dimensionbg.add(d2rb);
        d2rb.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(d2rb, org.openide.util.NbBundle.getMessage(DimensionsPanel.class, "DimensionsPanel.d2rb.text")); // NOI18N

        dimensionbg.add(d3rb);
        org.openide.awt.Mnemonics.setLocalizedText(d3rb, org.openide.util.NbBundle.getMessage(DimensionsPanel.class, "DimensionsPanel.d3rb.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(d2rb)
                    .addComponent(d3rb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(d2rb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(d3rb))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton d2rb;
    private javax.swing.JRadioButton d3rb;
    private javax.swing.ButtonGroup dimensionbg;
    // End of variables declaration//GEN-END:variables
}
