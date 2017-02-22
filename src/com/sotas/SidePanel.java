package com.sotas;

import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.checkbox.WebCheckBox;
import com.alee.laf.combobox.WebComboBox;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

public abstract class SidePanel extends WebPanel {
    private ComponentMap componentMap;
    final int rowHeight = 26;
    final int minRowHeight = 26;
    final int labelLen = 170;
    protected int fieldLen = 460;
    final int yCursorStep = 30;
    Color specialColor = new Color(181, 233, 255);
    public String prefix;
    int yCursor = 0;
    int xCursor = 0;
    protected String labelSuffix = "";

    public SidePanel(ComponentMap componentMap) {
        this.componentMap = componentMap;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
    }


    protected JComponent createRow(String label) {
        return createRow(label, genTextField());
    }

    protected <T extends JComponent> T createRow(String label, T field) {
        addElement(genLabel(label + labelSuffix), 1);
        addElement(field, 1);
        String key = label;
        if(prefix != null) key = prefix + "_" + label;
        componentMap.add(key, field);
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
        WebTextField res = new WebTextField();
        res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        return res;
    }

    protected JLabel genLabel(String label) {
        WebLabel l = new WebLabel(label);
        l.setPreferredSize(labelLen, rowHeight);
        l.setMinimumSize(new Dimension(labelLen, minRowHeight));
        return l;
    }

    protected JTextField genNumericField() {
        DecimalFormat format = new DecimalFormat();
        format.setGroupingUsed(false);
        JFormattedTextField res = new JFormattedTextField(format);
        res.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        return res;
    }

    protected JComboBox genComboBox(String[] list) {
        WebComboBox res = new WebComboBox(list);
        res.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        return res;
    }

    protected SpecialField genSpecialField(AbstractDialog dialog) {
        SpecialField f = new SpecialField(dialog);
        f.setPreferredSize(110, rowHeight);
        f.setMinimumSize(new Dimension(110, minRowHeight));
        f.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        return f;
    }

    @Deprecated
    protected SpecialField genSpecialField() {
        SpecialField f = new SpecialField();
        f.setPreferredSize(110, rowHeight);
        f.setMinimumSize(new Dimension(110, minRowHeight));
        f.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        return f;
    }

    protected ListField genListField(ListDialog listDialog) {
        ListField f = new ListField(listDialog);
        f.setMinimumSize(new Dimension(110, minRowHeight));
        return f;
    }

    protected WebDateField genDateField() {
        WebDateField res = new WebDateField();
        try {
            res.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##.##.####")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        res.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        return res;
    }

    protected JCheckBox genCheckBox(String text) {
        WebCheckBox res = new WebCheckBox(text);
        res.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        return res;
    }

    protected JButton genButton(String text) {
        return new WebButton(text);
    }

    public ComponentMap getComponentMap() {
        return componentMap;
    }

    public String genShortText() {
        StringBuilder sb = new StringBuilder();
        for(String key : getComponentMap().getMap().keySet()) {
            String val = getComponentMap().getFieldText(key);
            if(val != null && val.length() > 0) {
                if(prefix == null || key.startsWith(prefix)) {
                    sb.append(",").append(val);
                }
            }
        }
        if(sb.length()>0) sb.delete(0, 1);
        return sb.toString();
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
