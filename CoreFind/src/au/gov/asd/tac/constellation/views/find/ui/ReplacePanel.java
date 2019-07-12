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
package au.gov.asd.tac.constellation.views.find.ui;

import au.gov.asd.tac.constellation.graph.Attribute;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 * replace panel within the find top component
 *
 * @author twinkle2_little
 */
public class ReplacePanel extends javax.swing.JPanel {

    /**
     * Creates new form ReplacePanel
     */
    private final JPanel dropDownPanel;
    private final JScrollPane scrollPane;
    private final int INITIAL_DROPDOWN_WIDTH = 150;
    private final int INITIAL_DROPDOWN_HEIGHT = 200;
    private final String ATTRIBUTE_COUNT = " Attribute(s) Selected";
    private ArrayList<Attribute> attributes = new ArrayList<>();
    private final JCheckBox selectAll = new JCheckBox("All");
    private final HashSet<String> selectedAttributes = new HashSet<>();
    private final Color RED_COLOR = Color.red.darker();
    private final Color GREEN_COLOR = Color.green.darker();
    private ValidationListener validationListener;
    private boolean currValidity = false;

    public ReplacePanel() {
        initComponents();
        inputTypeGroup.add(stdTextRadioButton);
        inputTypeGroup.add(regexRadioButton);

        dropDownPanel = new JPanel();
        scrollPane = new JScrollPane(dropDownPanel);
        selectAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckAll(selectAll.isSelected());
            }
        });
        selectAll.setEnabled(false);
        setupComboBox();
        updateValidation();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        inputTypeGroup = new javax.swing.ButtonGroup();
        attributeDropDownMenu = new javax.swing.JPopupMenu();
        stdTextRadioButton = new javax.swing.JRadioButton();
        findTextField = new javax.swing.JTextField();
        replaceTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        regexRadioButton = new javax.swing.JRadioButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 32767));
        attributeDropdownButton = new javax.swing.JButton();
        ignorecaseCheckbox = new javax.swing.JCheckBox();
        attributeCountLabel = new javax.swing.JLabel();

        attributeDropDownMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {3};
        layout.rowHeights = new int[] {8};
        setLayout(layout);

        stdTextRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(stdTextRadioButton, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.stdTextRadioButton.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        add(stdTextRadioButton, gridBagConstraints);

        findTextField.setText(org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.findTextField.text")); // NOI18N
        findTextField.setToolTipText(org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.findTextField.toolTipText")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(findTextField, gridBagConstraints);

        replaceTextField.setText(org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.replaceTextField.text")); // NOI18N
        replaceTextField.setToolTipText(org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.replaceTextField.toolTipText")); // NOI18N
        replaceTextField.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 10);
        add(replaceTextField, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.jLabel1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        add(jLabel1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.jLabel2.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(jLabel2, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.jLabel3.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(jLabel3, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(regexRadioButton, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.regexRadioButton.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        add(regexRadioButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(attributeDropdownButton, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.attributeDropdownButton.text")); // NOI18N
        attributeDropdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attributeDropdownButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 10, 0);
        add(attributeDropdownButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(ignorecaseCheckbox, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.ignorecaseCheckbox.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(ignorecaseCheckbox, gridBagConstraints);

        attributeCountLabel.setForeground(new java.awt.Color(204, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(attributeCountLabel, org.openide.util.NbBundle.getMessage(ReplacePanel.class, "ReplacePanel.attributeCountLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        add(attributeCountLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void attributeDropdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attributeDropdownButtonActionPerformed
        attributeDropDownMenu.show(attributeDropdownButton, 0, attributeDropdownButton.getHeight());
    }//GEN-LAST:event_attributeDropdownButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attributeCountLabel;
    private javax.swing.JPopupMenu attributeDropDownMenu;
    private javax.swing.JButton attributeDropdownButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JTextField findTextField;
    private javax.swing.JCheckBox ignorecaseCheckbox;
    private javax.swing.ButtonGroup inputTypeGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton regexRadioButton;
    private javax.swing.JTextField replaceTextField;
    private javax.swing.JRadioButton stdTextRadioButton;
    // End of variables declaration//GEN-END:variables

    private void updateComboBox() {
        dropDownPanel.removeAll();
        resetComboBox();
        selectAll.setEnabled(attributes.size() > 0);

        for (Attribute a : attributes) {
            JCheckBox attrCheckbox = new JCheckBox(a.getName());
            attrCheckbox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox temp = (JCheckBox) e.getSource();
                    checkBoxValueChanged(temp.getText(), temp.isSelected());
                }
            });
            dropDownPanel.add(attrCheckbox);
        }

    }

    private void checkBoxValueChanged(String label, Boolean state) {
        if (state) {
            selectedAttributes.add(label);
        } else {
            selectedAttributes.remove(label);
        }

        int attributeSize = selectedAttributes.size();

        selectAll.setSelected(attributeSize == attributes.size());

        attributeCountLabel.setText(String.valueOf(attributeSize) + ATTRIBUTE_COUNT);

        if (attributeSize > 0) {
            attributeCountLabel.setForeground(GREEN_COLOR);
        } else {
            attributeCountLabel.setForeground(RED_COLOR);
        }

        updateValidation();

    }

    private void resetComboBox() {
        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        Dimension sepDim = new Dimension(Integer.MAX_VALUE, 5);
        sep.setMaximumSize(sepDim);
        selectAll.setSelected(false);
        dropDownPanel.add(selectAll);
        dropDownPanel.add(sep);
        updateValidation();
    }

    private void toggleCheckAll(Boolean selected) {
        Component[] components = dropDownPanel.getComponents();
        for (int i = 1; i < components.length; i++) {
            if (components[i] instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) components[i];
                cb.setSelected(selected);
                checkBoxValueChanged(cb.getText(), cb.isSelected());
            }
        }
    }

    public void updateAttributes(ArrayList<Attribute> attributes) {
        ArrayList<Attribute> stringAttributes = new ArrayList<>();

        for (Attribute a : attributes) {
            if (a.getAttributeType().equals("string")) {
                stringAttributes.add(a);
            }

        }

        this.attributes = stringAttributes;
        Collections.sort(this.attributes, new Comparator<Attribute>() {
            @Override
            public int compare(final Attribute o1, final Attribute o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        updateComboBox();
    }

    private void setupComboBox() {

        Dimension dropdownSize = new Dimension(INITIAL_DROPDOWN_WIDTH, INITIAL_DROPDOWN_HEIGHT);

        BoxLayout layout = new BoxLayout(dropDownPanel, BoxLayout.Y_AXIS);
        dropDownPanel.setLayout(layout);
        dropDownPanel.setMaximumSize(new Dimension(dropdownSize.width, Integer.MAX_VALUE));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setMaximumSize(dropdownSize);
        scrollPane.setPreferredSize(dropdownSize);
        scrollPane.setViewportView(dropDownPanel);

        attributeDropDownMenu.setMaximumSize(dropdownSize);
        attributeDropDownMenu.setPreferredSize(dropdownSize);
        attributeDropDownMenu.add(scrollPane);
        resetComboBox();

    }

    public ArrayList<Attribute> getSelectedAttributes() {
        if (selectAll.isSelected()) {
            return attributes;
        }
        ArrayList<Attribute> result = new ArrayList<>();
        for (String s : selectedAttributes) {
            for (Attribute a : attributes) {
                if (a.getName().equals(s)) {
                    result.add(a);
                }
            }
        }
        return result;
    }

    public String getFindString() {
        return findTextField.getText();
    }

    public Boolean getRegex() {
        return regexRadioButton.isSelected();
    }

    public String getReplaceString() {
        return replaceTextField.getText();
    }

    public Boolean getIgnorecase() {
        return ignorecaseCheckbox.isSelected();
    }

    public void reset() {
        attributeCountLabel.setText(0 + ATTRIBUTE_COUNT);
        attributeCountLabel.setForeground(RED_COLOR);
        findTextField.setText("");
        replaceTextField.setText("");
        updateComboBox();
        selectedAttributes.clear();
        ignorecaseCheckbox.setSelected(false);
        stdTextRadioButton.setSelected(true);

    }

    public void setValidationListener(ValidationListener listener) {
        this.validationListener = listener;
    }

    private void updateValidation() {

        if (validationListener != null) {

            boolean newValidity = selectedAttributes.size() > 0;
            currValidity = newValidity;
            validationListener.validityChanged(currValidity);
        }
    }

    public boolean getValidity() {
        return currValidity;
    }

    @Override
    public void setEnabled(boolean enable) {
        super.setEnabled(enable);
        for (Component c : getComponents()) {
            c.setEnabled(enable);
        }
        updateValidation();

    }
}
