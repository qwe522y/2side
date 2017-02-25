package com.sotas;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListDialog extends AbstractDialog {
    protected final WebTable table;
    public ListField field;
    private String[][] rows;

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

        WebButton okBut = new WebButton("OK");
        okBut.setPreferredSize(new java.awt.Dimension(80, 30));
        okBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(field != null) field.setText(rows[table.getSelectedRow()][0]);
                dispose();
            }
        });
        WebButton cancelBut = new WebButton("Отмена");
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
}
