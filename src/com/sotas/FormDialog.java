package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FormDialog extends AbstractDialog {
    public FormDialog(SidePanel form, String title, Dimension size) {
        setTitle(title);
        setLayout(new BorderLayout());
        setModal(true);
        add(form, BorderLayout.CENTER);
        WebButton okBut = new WebButton("OK");
        okBut.setPreferredSize(new Dimension(80, 30));
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
