package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    Admin admin = new Admin();
    ProductManager productManager = new ProductManager();


    public LoginPanel() {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(500, 200);
        jFrame.setContentPane(panel1);
        jFrame.setVisible(true);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
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
                if (!usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    // Admin.
                    if (usernameTextField.getText().equals(adminUsername) && passwordField.getText().equals(adminPassword)) {
                        AdminPanel adminPanel = new AdminPanel();
                    }
                    // Customer/Employee.
                    String username = usernameTextField.getText();
                    String password = passwordField.getText();

                    for (User user : admin.UserList) {
                        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                            if (user instanceof Customer) { // If it's a customer.
                                CustomerPanel customerPanel = new CustomerPanel(productManager, (Customer) user);
                                break;
                            } else if (user instanceof Employee) { // If it's an employee.
                                EmployeePanel employeePanel = new EmployeePanel(productManager, admin, (Employee) user);
                                break;
                            }
                        }
                        break;
                    }
                } else {
                    messageLabel.setText("Wrong credentials.");
                }
            }
        });
    }
}
