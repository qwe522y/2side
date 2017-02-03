package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FormDialog extends AbstractDialog {
    public FormDialog(SpecialField field, final SidePanel form, String title, Dimension size) {
        super(form.getComponentMap());
        this.field = field;
        setTitle(title);
        setLayout(new BorderLayout());
        setModal(true);
        WebScrollPane scrollPane = new WebScrollPane(form);
        scrollPane.setBorder(null);
        add(new JPanel(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        WebButton okBut = new WebButton("OK");
        okBut.setPreferredSize(new Dimension(80, 30));
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if(FormDialog.this.field != null) FormDialog.this.field.setText(form.genShortText());
            }
        });
        WebButton cancelBut = new WebButton("Отмена");
        cancelBut.setPreferredSize(new Dimension(80, 30));
        cancelBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        WebPanel butPanel = new WebPanel();
        butPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        butPanel.add(okBut);
        butPanel.add(cancelBut);
        add(butPanel, BorderLayout.SOUTH);
        add(new JPanel(), BorderLayout.LINE_START);
        add(new JPanel(), BorderLayout.LINE_END);
        setSize(size);
    }
}
