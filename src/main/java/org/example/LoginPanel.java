package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPanel {
    private JPanel panel1;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;
    private String adminUsername = "admin";
    private String adminPassword = "1234";

    public LoginPanel(ProductManager productManager, Admin admin) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(500, 200);
        jFrame.setContentPane(panel1);
        jFrame.setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean exists = false;

                // Check customer list if username exists.
                for (User user : admin.CustomerList) {
                    if (usernameTextField.getText().equals(user.getUsername())) {
                        exists = true;
                        messageLabel.setText("Username already exists!");
                        break;
                    }
                }

                // Check employee list if username exists.
                for (User user : admin.EmployeeList) {
                    if (usernameTextField.getText().equals(user.getUsername())) {
                        exists = true;
                        messageLabel.setText("Username already exists!");
                        break;
                    }
                }

                // Register a new customer if text field is not empty and username is available.
                if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty() && !exists) {
                    String username = usernameTextField.getText();
                    String password = passwordField.getText();
                    admin.AddCustomer(username, password, 500);
                    messageLabel.setText("Successfully registered!");
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    // Admin.
                    if (usernameTextField.getText().equals(adminUsername) && passwordField.getText().equals(adminPassword)) {
                        AdminPanel adminPanel = new AdminPanel(admin, productManager);
                        usernameTextField.setText("");
                        passwordField.setText("");
                        jFrame.dispose();
                    }
                    // Customer/Employee.
                    String username = usernameTextField.getText();
                    String password = passwordField.getText();
                    for (Customer user : admin.CustomerList) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            CustomerPanel customerPanel = new CustomerPanel(productManager, user, admin);
                            found = true;
                            jFrame.dispose();
                        }
                    }
                    for (Employee emp : admin.EmployeeList) {
                        if (emp.getUsername().equals(username) && emp.getPassword().equals(password)) {
                            EmployeePanel employeePanel = new EmployeePanel(productManager, admin, emp);
                            found = true;
                            jFrame.dispose();
                        }
                    }
                }
                if(!found) {
                    messageLabel.setText("Wrong credentials.");
                }
            }
        });

        usernameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });
    }
}
