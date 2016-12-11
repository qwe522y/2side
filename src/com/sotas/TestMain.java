package com.sotas;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class TestMain {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WebLookAndFeel());
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                new MyPanel().setVisible(true);
            }
        });
    }
}

class MyPanel extends JFrame {
    public MyPanel() throws HeadlessException {
        setTitle("test");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(null);

        WebTable t = new WebTable(new String[][]{{"111", "qwe"}, {"222", "asd"}, {"333", "zxc"},{"111", "qwe"}, {"222", "asd"}, {"333", "zxc"}, {"111", "qwe"}, {"222", "asd"}, {"333", "zxc"},{"111", "qwe"}, {"222", "asd"}, {"333", "zxc"}}, new String[]{"Oooo", "Pppp"});
        t.setRowSelectionAllowed ( true );
        t.setColumnSelectionAllowed ( false );
        //t.setPreferredScrollableViewportSize ( new Dimension ( 300, 100 ) );
        t.setEditable ( false );
        t.setSelectedRow(0);
        WebScrollPane pane = new WebScrollPane(t);
        pane.setBounds(10, 10, 300, 150);
        add(pane);
        setSize(600, 600);
    }
}
