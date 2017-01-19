package com.sotas;

import com.alee.laf.optionpane.WebOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LoginFrame extends JFrame{
    private static String login;
    private static String password;
    private ServerCmd serverCmd = new ServerCmd();
    private JTextField loginField;
    private JTextField passwordField;

    public LoginFrame() {
        setTitle("Вход");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(275, 135);
        final JLabel loginLabel = new JLabel("Логин:");
        loginLabel.setBounds(10, 10, 100, 30);
        add(loginLabel);
        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setBounds(10, 40, 100, 30);
        add(passwordLabel);
        loginField = new JTextField();
        loginField.setBounds(110, 10, 150, 30);
        add(loginField);
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 40, 150, 30);
        add(passwordField);
        final JButton button = new JButton("Войти");
        button.setBounds(180, 70, 80, 30);
        add(button);
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processLogin();
            }
        });
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                processLogin();
            }
        });
    }

    public void processLogin() {
        try {
            if (serverCmd.sendAuthRequest(loginField.getText(), passwordField.getText())) {
                login = loginField.getText();
                password = passwordField.getText();
                dispose();
                new MainFrame().setVisible(true);
            } else {
                WebOptionPane.showMessageDialog(null, "Неверный логин или пароль", "Ошибка авторизации", WebOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            WebOptionPane.showMessageDialog(null, "Возникла внутренняя ошибка\nmessage: " + e.getMessage() + "\n" + new String(baos.toByteArray()), "Ошибка", WebOptionPane.ERROR_MESSAGE);
        }
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }
}
