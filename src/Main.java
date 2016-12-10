import com.alee.laf.WebLookAndFeel;
import com.sotas.MainPanel;
import com.sotas.Resolution;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WebLookAndFeel());
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(Resolution.w, Resolution.h);
                f.add(new MainPanel(f));
                f.setTitle("Тест");
                f.setMinimumSize(new Dimension(Resolution.w, Resolution.h));
                f.setVisible(true);
            }
        });
    }
}
