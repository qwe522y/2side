package com.sotas;

import com.alee.extended.date.WebDateField;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.spinner.WebSpinner;
import com.alee.laf.text.WebTextField;

import javax.swing.*;
import java.awt.*;

public abstract class SidePanel extends WebPanel {
    private ComponentMap componentMap;
    enum Type {TextField, Spinner, ComboBox, DateField, Special}
    final int rowHeight = 30;
    final int labelLen = 170;
    final int fieldLen = 460;
    final int yCursorStep = 30;
    Color specialColor = new Color(181, 233, 255);
    int yCursor = 0;
    int xCursor = 0;

    public SidePanel(ComponentMap componentMap) {
        this.componentMap = componentMap;
        //setBackground(Color.YELLOW);
        setLayout(null);
    }

    protected void createRowLabel(String label) {
        WebLabel l = new WebLabel(label);
        l.setBounds(0, yCursor, labelLen + fieldLen, rowHeight);
        add(l);
        yCursor += yCursorStep;
    }

    protected void createRow(String label, Type type, Color color) {
        xCursor = 0;
        createLabel(label, labelLen);
        JComponent component = null;
        if(type == null) type = Type.Special;
        switch (type) {
            case ComboBox:
                component = createComboBox(fieldLen);
                break;
            case TextField:
                component = createTextField(fieldLen);
                break;
            case Spinner:
                component = createSpinner(fieldLen);
                break;
            case DateField:
                component = createDateField(fieldLen);
                break;
            default:
                component = createSpecialField(fieldLen);
        }
        if(color != null) component.setBackground(color);
        componentMap.add(label, component);
        yCursor += yCursorStep;
    }

    protected void createRow(String label,  Type type) {
        createRow(label, type, null);
    }
    protected void createRow(String label,  Color color) {
        createRow(label, null, color);
    }
    protected void createRow(String label) {
        createRow(label, null, null);
    }

    protected JTextField createTextField(int w) {
        JTextField tf = new WebTextField();
        tf.setBounds(xCursor, yCursor, w, rowHeight);
        add(tf);
        xCursor += w;
        return tf;
    }

    protected void createLabel(String label, int w) {
        WebLabel tf = new WebLabel(label);
        tf.setBounds(xCursor, yCursor, w, rowHeight);
        add(tf);
        xCursor += w;
    }

    protected JSpinner createSpinner(int w) {
        JSpinner tf = new WebSpinner(new SpinnerNumberModel(0, 0, 99999, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(tf);
        editor.getFormat().setGroupingUsed(false);
        tf.setEditor(editor);
        tf.setBounds(xCursor, yCursor, w, rowHeight);
        add(tf);
        xCursor += w;
        return tf;
    }

    protected JComboBox createComboBox(int w) {
        WebComboBox tf = new WebComboBox();
        tf.setBounds(xCursor, yCursor, w, rowHeight);
        add(tf);
        xCursor += w;
        return tf;
    }

    protected SpecialField createSpecialField(int w) {
        SpecialField tf = new SpecialField();
        tf.setBounds(xCursor, yCursor, w, rowHeight);
        add(tf);
        xCursor += w;
        return tf;
    }

    protected WebDateField createDateField(int w) {
        WebDateField tf = new WebDateField();
        tf.setBounds(xCursor, yCursor, w, rowHeight);
        add(tf);
        xCursor += w;
        return tf;
    }

    public ComponentMap getComponentMap() {
        return componentMap;
    }
}
