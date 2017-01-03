package com.sotas;

import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, UnsupportedLookAndFeelException {
        Resource.getInstance();
        UIManager.setLookAndFeel(new WebLookAndFeel());
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                //f.setMinimumSize(new Dimension(1366, 730));
                MainFrame f = new MainFrame();
                f.setVisible(true);
            }
        });
    }
}
