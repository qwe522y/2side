package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListDialog extends AbstractDialog {
    protected final WebTable table;
    public ListField field;
    protected String[][] rows;
    public final JButton okBut;
    public final WebButton cancelBut;

    public ListDialog(final String[][] rows, String[] headers, String title, Dimension size) {
        super(null);
        this.rows = rows;
        setTitle(title);
        setLayout(new BorderLayout());
        setModal(true);
        if(headers == null) {
            headers = new String[rows[0].length];
            for(int i=0; i<headers.length; i++) headers[i] = "";
        }
        table = new WebTable(rows, headers);
        table.setRowSelectionAllowed ( true );
        table.setColumnSelectionAllowed ( false );
        table.setEditable ( false );
        table.setSelectedRow(0);
        WebScrollPane pane = new WebScrollPane(table);
        add(pane, BorderLayout.CENTER);

        okBut = new WebButton("OK");
        okBut.setPreferredSize(new java.awt.Dimension(80, 30));
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(field != null) field.setText(rows[table.getSelectedRow()][0]);
                dispose();
            }
        });
        cancelBut = new WebButton("Отмена");
        cancelBut.setPreferredSize(new java.awt.Dimension(80, 30));
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
        setSize(size);
    }

    public String[][] getRows() {
        return rows;
    }

    @Override
    public void setVisible(boolean b) {
        if(field != null) {
            String txt = field.getText();
            boolean stop = false;
            for (int i = 0; i < table.getModel().getRowCount() && !stop; i++) {
                for (int j = 0; j < table.getModel().getColumnCount() && !stop; j++) {
                    if (table.getModel().getValueAt(i, j).equals(txt)) {
                        table.setSelectedRow(i);
                        stop = true;
                    }
                }
            }
        }
        super.setVisible(b);
    }
}
