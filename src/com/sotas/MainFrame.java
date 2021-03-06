package com.sotas;

import com.alee.extended.date.WebDateField;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
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

public class MainFrame extends WebFrame {
    private static final Logger log = Logger.getLogger(MainFrame.class);
    ServerCmd serverCmd = new ServerCmd();
    ComponentMap cm = new ComponentMap("main");
    ComponentMap vladelecCm = new ComponentMap(StrConst.Владелец._name);
    ComponentMap predstavitelCm = new ComponentMap(StrConst.Представитель_собственника._name);
    ComponentMap PTSCm = new ComponentMap(StrConst.PTS._name);
    ComponentMap svidRegCm = new ComponentMap(StrConst.Свидетельство_о_регистрации._name);
    ComponentMap kvitanciyaCm = new ComponentMap(StrConst.Квитанция_об_оплате._name);
    ComponentMap documentOsnovanieCm = new ComponentMap(StrConst.ДокументОснование._name);

    public MainFrame() {
        setIconImage(Resource.icon);
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

        SidePanel rightSide = new RightSidePanel(cm, PTSCm, svidRegCm, kvitanciyaCm, documentOsnovanieCm);
        c.weightx = 0.5; c.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(rightSide, c);
    }

    private void createToolBar() {
        WebToolBar toolBar = new WebToolBar();
        toolBar.setToolbarStyle(ToolbarStyle.attached);
        toolBar.setFloatable(false);
        toolBar.add(new WebLabel(" Дата: "));
        WebDateField dateField = new WebDateField(new Date());
        dateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
            }
        });
        dateField.setMinimumWidth(150);
        toolBar.add(dateField);
        cm.add(StrConst.date, dateField);
        final JButton newDocButton = new JButton(new ImageIcon(getClass().getResource ( "/img/new_doc16.png" )));
        toolBar.add(newDocButton);
        JButton printButton = new JButton(new ImageIcon(getClass().getResource ( "/img/printer16.png" )));
        toolBar.add(printButton);
        newDocButton.setFocusable(false);
        newDocButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = WebOptionPane.showConfirmDialog(null, "Создать новый документ?", "Создание нового документа", WebOptionPane.YES_NO_OPTION);
                log.info("press newDocButton res="+res);
                if(res == 0) { //yes
                    clearAllCm();
                    dispose();
                    new MainFrame().setVisible(true);
                }
            }
        });
        printButton.setFocusable(false);
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                final Properties prop = new Properties();
                try(InputStream fis = new FileInputStream("setting.cfg")) {
                    prop.load(fis);
                    final PodrazdelenieDialog podrazdelenieDialog = new PodrazdelenieDialog(cm, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                Prms prms = serverCmd.sendRegisterRequest(LoginFrame.getLogin(), LoginFrame.getPassword(), new Prms(
                                        cm.toStringMap(),
                                        vladelecCm.toStringMap(),
                                        predstavitelCm.toStringMap(),
                                        PTSCm.toStringMap(),
                                        svidRegCm.toStringMap(),
                                        kvitanciyaCm.toStringMap(),
                                        documentOsnovanieCm.toStringMap()
                                ));
                                clearAllCm();
                                new PdfGenerator(prms).gen();
                                dispose();
                                log.info(prop.getProperty("pdfReader") + " temp.pdf");
                                Process process = Runtime.getRuntime().exec(new String[]{prop.getProperty("pdfReader"), "temp.pdf"});
                                process.waitFor();
                                new MainFrame().setVisible(true);
                            } catch (Exception ex) {
                                log.error(ex.getMessage(), ex);
                                Utils.showException(ex);
                            }
                        }
                    });
                    podrazdelenieDialog.setVisible(true);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    Utils.showException(ex);
                }
            }
        });
        add(toolBar, BorderLayout.PAGE_START);
    }

    private void clearAllCm() {
        cm.getMap().clear();
        vladelecCm.getMap().clear();
        predstavitelCm.getMap().clear();
        PTSCm.getMap().clear();
        svidRegCm.getMap().clear();
        kvitanciyaCm.getMap().clear();
        documentOsnovanieCm.getMap().clear();
    }
}
