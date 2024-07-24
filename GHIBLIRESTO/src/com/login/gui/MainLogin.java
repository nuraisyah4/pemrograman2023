package com.login.gui;

import com.AdminPage.DashboardAdmin;
import com.CostomerPage.CustomerPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLogin {
    private JPanel Image;
    private JButton LoginWeitersBtn;
    private JButton CostumerBtn;
    private JTextField Username;
    private JPasswordField Password;
    private JLabel AppName;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JPanel Container;

    public MainLogin() {

        LoginWeitersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin("admin");
            }
        });

        CostumerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin("customer");
            }
        });
    }

    private void handleLogin(String role) {
        String username = Username.getText();
        String password = new String(Password.getPassword());

        LoginHandler loginHandler = new LoginHandler();
        boolean isAuthenticated = loginHandler.authenticate(username, password, role);

        if (isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Login Successful as " + role);
            navigateToRolePage(role);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username, Password, or Role");
        }
    }

    private void navigateToRolePage(String role) {
        JFrame nextFrame = null;
        if ("admin".equalsIgnoreCase(role)) {
            nextFrame = new DashboardAdmin();
        } else if ("customer".equalsIgnoreCase(role)) {
            nextFrame = new CustomerPage(); // Menambahkan CustomerPage
        }

        if (nextFrame != null) {
            nextFrame.setVisible(true);
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(Container);
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainLogin");
        frame.setContentPane(new MainLogin().Container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
