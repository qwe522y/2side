package com.sotas;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebTextField;

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

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx=0.5;

        c.gridx=0; c.gridy=0; c.anchor = GridBagConstraints.LINE_START; c.fill = GridBagConstraints.HORIZONTAL;
        add(genLabel(), c);
        c.gridx=1; c.gridy=0; c.anchor = GridBagConstraints.LINE_END; c.fill = GridBagConstraints.NONE;
        add(genField(), c);

        c.gridx=0; c.gridy=1; c.anchor = GridBagConstraints.LINE_START; c.fill = GridBagConstraints.HORIZONTAL;
        add(genLabel(), c);
        c.gridx=1; c.gridy=1; c.anchor = GridBagConstraints.LINE_END; c.fill = GridBagConstraints.NONE;
        add(genField(), c);

        c.gridx=0; c.gridy=2; c.anchor = GridBagConstraints.LINE_START; c.fill = GridBagConstraints.HORIZONTAL;
        add(genLabel(), c);
        c.gridx=1; c.gridy=2; c.anchor = GridBagConstraints.LINE_END; c.fill = GridBagConstraints.NONE;
        add(genField(), c);
        pack();
    }

    JComponent genLabel() {
        WebLabel res = new WebLabel("Some text!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        res.setPreferredWidth(150);
        return res;
    }

    JComponent genField() {
        WebTextField res = new WebTextField();
        res.setPreferredWidth(400);
        return  res;
    }
}
