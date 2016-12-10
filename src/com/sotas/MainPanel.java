package com.sotas;

import com.alee.extended.date.WebDateField;
import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;
import com.alee.laf.toolbar.ToolbarStyle;
import com.alee.laf.toolbar.WebToolBar;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class MainPanel extends WebPanel {
    private static final Logger log = Logger.getLogger(MainPanel.class);
    ComponentMap componentMap = new ComponentMap();
    public MainPanel(Component parent) {
        setSize(parent.getWidth(), parent.getHeight());
        setLayout(null);

        WebToolBar toolBar = new WebToolBar();
        toolBar.setBounds(0,0, 2600, 35);
        toolBar.setToolbarStyle(ToolbarStyle.attached);
        toolBar.setFloatable(false);
        toolBar.add(new WebLabel(" "+ StrConst.zayavlenie_nomer +" "));
        WebTextField f = new WebTextField();
        f.setMinimumWidth(150);
        toolBar.add(f);
        componentMap.add(StrConst.zayavlenie_nomer, f);
        toolBar.add(new WebLabel(" Дата: "));
        WebDateField dateField = new WebDateField(new Date());
        dateField.setMinimumWidth(150);
        toolBar.add(dateField);
        componentMap.add(StrConst.date, dateField);
        JButton printButton = new WebButton(new ImageIcon(getClass().getResource ( "/img/printer16.png" )));
        toolBar.add(printButton);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties prop = new Properties();
                try(InputStream fis = new FileInputStream("setting.cfg")) {
                    prop.load(fis);
                    new PdfGenerator(componentMap).gen();
                    log.info(prop.getProperty("pdfReader") + " " + "temp.pdf");
                    Runtime.getRuntime().exec(new String[]{prop.getProperty("pdfReader"), "temp.pdf"});
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                }
            }
        });
        add(toolBar);

        WebPanel formPanel = new WebPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(5, 40, 1285, 820);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(formPanel);

        SidePanel leftSide = new LeftSidePanel(componentMap);
        leftSide.setBounds(5, 5, 630, 810);
        formPanel.add(leftSide);

        VerticalSeparator sep = new VerticalSeparator(810);
        sep.setLocation(630, 5);
        formPanel.add(sep);

        SidePanel rightSide = new RightSidePanel(componentMap);
        rightSide.setBounds(650, 5, 630, 810);
        formPanel.add(rightSide);

        JButton but = new WebButton("Найти заявление(F4)");
        but.setBounds(10, 865, 160, 30);
        add(but);

        but = new WebButton("Найти ТС(Shift+F4)");
        but.setBounds(170, 865, 160, 30);
        add(but);

        but = new WebButton("Новое заявление(Сtrl+N)");
        but.setBounds(340, 865, 180, 30);
        add(but);
    }
}
