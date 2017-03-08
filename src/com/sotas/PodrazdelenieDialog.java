package com.sotas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PodrazdelenieDialog extends ListDialog {
    public PodrazdelenieDialog(final ComponentMap cm, final ActionListener actionListener) {
        super(new String[][]{
            {"МРЭО ГИБДД МВД по РД(дислокация г.Избербаш)"},
            {"МРЭО ГИБДД МВД по РД(дислокация г.Кизильюрт)"},
            {"МРЭО ГИБДД МВД по РД(дислокация г.Кизляр)"},
            {"МРЭО ГИБДД МВД по РД(дислокация г.Махачкала)"},
            {"МРЭО ГИБДД МВД по РД(дислокация г.Хасавьюрт)"},
            {"МРЭО ГИБДД МВД по РД(дислокация с.Леваши)"},
            {"МРЭО ГИБДД МВД по РД(дислокация с.Хунзах)"},
            {"МРЭО ГИБДД МВД по РД(дислокация г.Буйнакск)"},
            {"МРЭО ГИБДД МВД по РД(дислокация г.Дербент)"},
        }, null, "Подразделения", new Dimension(600, 400));
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.add(StrConst.представитель, new JTextField(rows[table.getSelectedRow()][0]));
                actionListener.actionPerformed(e);
            }
        });
    }
}
