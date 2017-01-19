package com.sotas;

import com.alee.laf.WebLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, UnsupportedLookAndFeelException {
        Resource.getInstance();
        UIManager.setLookAndFeel(new WebLookAndFeel());
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                //f.setMinimumSize(new Dimension(1366, 730));
                //MainFrame f = new MainFrame();
                //f.setVisible(true);
                LoginFrame f = new LoginFrame();
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
                f.setLocation(x, y);
                f.setVisible(true);
            }
        });
    }
}
