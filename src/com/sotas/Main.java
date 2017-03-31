package com.sotas;

import com.alee.laf.WebLookAndFeel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    private static final Logger log = Logger.getLogger(MainFrame.class);
    public static void main(String[] args) throws InvocationTargetException, InterruptedException, UnsupportedLookAndFeelException {
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
            Resource.getInstance();
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    /*
                    new MainFrame().setVisible(true);
                    if(true) return;
                    */
                    LoginFrame f = new LoginFrame();
                    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (int) ((dimension.getWidth() - f.getWidth()) / 2);
                    int y = (int) ((dimension.getHeight() - f.getHeight()) / 2);
                    f.setLocation(x, y);
                    f.setVisible(true);
                }
            });
        } catch (Exception ex) {
            log.fatal(ex.getMessage(), ex);
            Utils.showException(ex);
        }
    }
}
