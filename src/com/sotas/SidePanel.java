package com.sotas;

import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebTextField;

import javax.swing.*;
import java.awt.*;

public abstract class SidePanel extends WebPanel {
    private ComponentMap componentMap;
    final int rowHeight = 28;
    final int labelLen = 170;
    final int fieldLen = 460;
    final int yCursorStep = 25;
    Color specialColor = new Color(181, 233, 255);
    int yCursor = 0;
    int xCursor = 0;

    public SidePanel(ComponentMap componentMap) {
        this.componentMap = componentMap;
        //setBackground(Color.YELLOW);
        setLayout(null);
    }


    protected JComponent createRow(String label) {
        return createRow(label, genTextField());
    }

    protected JComponent createRow(String label, JComponent field) {
        addElement(genLabel(label), labelLen);
        addElement(field, fieldLen);
        componentMap.add(label, field);
        rowBr();
        return field;
    }

    public void rowBr() {
        yCursor += yCursorStep;
        xCursor = 0;
    }

    protected JComponent addElement(JComponent element, int width) {
        element.setBounds(xCursor, yCursor, width, rowHeight);
        add(element);
        xCursor += element.getWidth();
        return element;
    }

    protected JTextField genTextField() {
        return new WebTextField();
    }

    protected JLabel genLabel(String label) {
        return new WebLabel(label);
    }

    protected JSpinner genSpinner() {
        JSpinner tf = new WebSpinner(new SpinnerNumberModel(0, 0, 99999, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(tf);
        editor.getFormat().setGroupingUsed(false);
        tf.setEditor(editor);
        return tf;
    }

    protected JComboBox genComboBox(String[] list) {
        return new WebComboBox(list);
    }

    protected SpecialField genSpecialField() {
        return new SpecialField();
    }

    protected WebDateField genDateField() {
        return new WebDateField();
    }

    protected JCheckBox genCheckBox(String text) {
        return new WebCheckBox(text);
    }

    protected JButton genButton(String text) {
        return new WebButton(text);
    }

    public ComponentMap getComponentMap() {
        return componentMap;
    }
}
