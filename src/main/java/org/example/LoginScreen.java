package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginScreen {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JPanel loginScreen;
    private JLabel invalidLabel;
    private JFrame jFrame;
    ProductManager productManager = new ProductManager();

    public LoginScreen(ArrayList<Customer> customerList){
        jFrame = new JFrame("Login Screen");
        jFrame.setContentPane(loginScreen);
        jFrame.setSize(500,300);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Customer c : customerList){
                    if(usernameField.getText().equals(c.getUsername()) && passwordField.getText().equals(c.getPassword())){
                        ShopWindow shopWindow = new ShopWindow(customerList, productManager.getProductList());
                        jFrame.dispose();
                    }
                    else{
                        invalidLabel.setText("Wrong credentials.");
                    }
                }
            }
        });
    }
}
