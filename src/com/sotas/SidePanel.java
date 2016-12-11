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
    final int rowHeight = 30;
    final int minRowHeight = 26;
    final int labelLen = 170;
    final int fieldLen = 460;
    final int yCursorStep = 30;
    Color specialColor = new Color(181, 233, 255);
    int yCursor = 0;
    int xCursor = 0;

    public SidePanel(ComponentMap componentMap) {
        this.componentMap = componentMap;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
    }


    protected JComponent createRow(String label) {
        return createRow(label, genTextField());
    }

    protected <T extends JComponent> T createRow(String label, T field) {
        addElement(genLabel(label), 1);
        addElement(field, 1);
        componentMap.add(label, field);
        rowBr();
        return field;
    }

    public void rowBr() {
        yCursor += yCursorStep;
        xCursor = 0;
    }

    protected <T extends JComponent> T addElement(T element, int cellWidth) {
        //element.setBounds(xCursor, yCursor, width, rowHeight);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = xCursor;
        c.gridy = yCursor;
        if(cellWidth == 2) {
            c.gridwidth = 2;
        }
        if(xCursor == 0) {
            c.anchor = GridBagConstraints.LINE_START;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
        } else {
            c.anchor = GridBagConstraints.LINE_END;
            c.fill = GridBagConstraints.NONE;
            element.setPreferredSize(new Dimension(fieldLen, rowHeight));
            element.setMinimumSize(new Dimension(fieldLen, minRowHeight));
        }
        add(element, c);
        xCursor += 1;
        return element;
    }

    protected JTextField genTextField() {
        return new WebTextField();
    }

    protected JLabel genLabel(String label) {
        WebLabel l = new WebLabel(label);
        l.setPreferredSize(110, rowHeight);
        l.setMinimumSize(new Dimension(110, minRowHeight));
        return l;
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
        SpecialField f = new SpecialField();
        f.setPreferredSize(110, rowHeight);
        f.setMinimumSize(new Dimension(110, minRowHeight));
        return f;
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


    class ComplexField extends WebPanel{
        public ComplexField() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        }

        <T extends JComponent> T add(T item, int pxWidth) {
            item.setPreferredSize(new Dimension(pxWidth, rowHeight));
            item.setMinimumSize(new Dimension(pxWidth, minRowHeight));
            item.setMaximumSize(new Dimension(pxWidth, rowHeight));
            add(item);
            return item;
        }

        JPanel addSpace(int pxWidth) {
            return add(new JPanel(), pxWidth);
        }
    }
}
