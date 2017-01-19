package com.sotas;

import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.text.WebTextField;
import com.alee.laf.toolbar.ToolbarStyle;
import com.alee.laf.toolbar.WebToolBar;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;

public class MainFrame extends WebFrame {
    private static final Logger log = Logger.getLogger(MainFrame.class);
    ServerCmd serverCmd = new ServerCmd();
    ComponentMap cm = new ComponentMap("main");
    ComponentMap vladelecCm = new ComponentMap("владелец");
    ComponentMap predstavitelCm = new ComponentMap("представитель");
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Гарибанов ГИБДД");
        setLayout(new BorderLayout());
        createToolBar();
        createCenterPanel();
        setMinimumSize(new Dimension(1185, 700));
        pack();
    }

    private void createCenterPanel() {
        WebPanel centerPanel = new WebPanel();
        centerPanel.setLayout(new GridBagLayout());
        //centerPanel.setBackground(Color.GRAY);
        add(centerPanel, BorderLayout.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(5, 5, 5, 5);

        SidePanel leftSide = new LeftSidePanel(cm, vladelecCm, predstavitelCm);
        c.weightx = 0.5; c.fill = GridBagConstraints.HORIZONTAL; ;
        centerPanel.add(leftSide, c);

        VerticalSeparator sep = new VerticalSeparator();
        c.weightx = 0.0; c.fill = GridBagConstraints.VERTICAL;;
        centerPanel.add(sep, c);

        SidePanel rightSide = new RightSidePanel(cm);
        c.weightx = 0.5; c.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(rightSide, c);
    }

    private void createToolBar() {
        WebToolBar toolBar = new WebToolBar();
        toolBar.setToolbarStyle(ToolbarStyle.attached);
        toolBar.setFloatable(false);
        toolBar.add(new WebLabel(" "+ StrConst.zayavlenie_nomer +" "));
        WebTextField f = new WebTextField();
        f.setMinimumWidth(150);
        toolBar.add(f);
        cm.add(StrConst.zayavlenie_nomer, f);
        toolBar.add(new WebLabel(" Дата: "));
        WebDateField dateField = new WebDateField(new Date());
        dateField.setMinimumWidth(150);
        toolBar.add(dateField);
        cm.add(StrConst.date, dateField);
        JButton printButton = new WebButton(new ImageIcon(getClass().getResource ( "/img/printer16.png" )));
        toolBar.add(printButton);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer docnum = null;
                try {
                    docnum = Integer.parseInt(cm.getFieldText(StrConst.zayavlenie_nomer));
                } catch (RuntimeException e) {
                    WebOptionPane.showMessageDialog (null, "Заполните номер заявления", "Ошибка", WebOptionPane.ERROR_MESSAGE );
                    return;
                }
                Properties prop = new Properties();
                try(InputStream fis = new FileInputStream("setting.cfg")) {
                    prop.load(fis);
                    Prms prms = serverCmd.sendRegisterRequest(LoginFrame.getLogin(), LoginFrame.getPassword(), docnum, new Prms(cm.toStringMap(), vladelecCm.toStringMap(), predstavitelCm.toStringMap()));
                    clearAllCm();
                    new PdfGenerator(prms).gen();
                    dispose();
                    log.info(prop.getProperty("pdfReader") + " temp.pdf");
                    Process process = Runtime.getRuntime().exec(new String[]{prop.getProperty("pdfReader"), "temp.pdf"});
                    process.waitFor();
                    new MainFrame().setVisible(true);
                } catch (Exception ex) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ex.printStackTrace(new PrintStream(baos));
                    WebOptionPane.showMessageDialog (null, "Возникла внутренняя ошибка\nmessage: " + ex.getMessage() + "\n" + new String(baos.toByteArray()) , "Ошибка", WebOptionPane.ERROR_MESSAGE );
                    log.error(ex.getMessage(), ex);
                }
            }
        });
        add(toolBar, BorderLayout.PAGE_START);
    }

    private void clearAllCm() {
        cm.getMap().clear();
        vladelecCm.getMap().clear();
        predstavitelCm.getMap().clear();
    }
}
