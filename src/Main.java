import com.alee.laf.WebLookAndFeel;
import com.sotas.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WebLookAndFeel());
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(1366, 700);
                f.add(new MainPanel(f));
                f.setTitle("Тест");
                f.setMinimumSize(new Dimension(1366, 730));
                f.setVisible(true);
                f.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        System.out.println(e);
                    }
                });
            }
        });
    }
}
