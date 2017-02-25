package com.sotas;

import ca.odell.glazedlists.GlazedLists;
import com.alee.laf.button.WebButton;
import com.alee.laf.combobox.WebComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListField extends JPanel {
    private final WebComboBox comboBox;
    private AutoCompleteSupport<String> val;
    private String[] items;
    public ListField(final ListDialog listDialog) {
        setLayout(new GridBagLayout());
        WebButton but = new WebButton(new ImageIcon(getClass().getResource("/img/list_button16.png")));
        comboBox = new WebComboBox();

        String[] items = firstColumnArray(listDialog.getRows());
        setItems(items);

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1; c.fill = GridBagConstraints.HORIZONTAL;
        add(comboBox, c);
        c.weightx = 0; c.fill = GridBagConstraints.NONE;
        add(but, c);
        listDialog.field = this;
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listDialog.setVisible(true);
            }
        });
        but.setFocusable(false);
    }

    private String[] firstColumnArray(String[][] rows) {
        String[] res = new String[rows.length];
        int i=0;
        for(String[] row : rows) {
            res[i++] = row[0];
        }
        return res;
    }

    public void setText(String text) {
        comboBox.setSelectedItem(text);
    }

    public String getText() {
        return val.getText();
    }

    public ListField setBg(Color color) {
        setBackground(color);
        return this;
    }

    public void setItems(String[] items) {
        if(is2ArrayEqual(this.items, items)) return;
        this.items = items;
        if(val != null) val.uninstall();
        val = AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(items));
    }

    private boolean is2ArrayEqual(String[] a, String[] b) {
        if(a == null || b == null) return false;
        if(a.length != b.length) return false;
        for(int i=0; i<a.length; i++) {
            if(!a[i].equals(b[i])) return false;
        }
        return true;
    }
}
