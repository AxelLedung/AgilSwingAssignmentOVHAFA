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
    private ArrayList<User> users = new ArrayList<>();
    private String adminUsername = "admin";
    private String adminPassword = "1234";


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
                    users.add(new Customer( username, password, 10000));
                    messageLabel.setText("Successfully registered!");
                    System.out.println(users.get(0).getDescription());
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
                    for (User user : users) {
                        if (user instanceof Customer) {
                            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                CustomerPanel customerPanel = new CustomerPanel();
                                break;
                            }
                        } else if (user instanceof Employee) {
                            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                EmployeePanel employeePanel = new EmployeePanel();
                                break;
                            }
                        }
                    }
                } else {
                    messageLabel.setText("Wrong credentials.");
                }
            }
        });
    }
}
