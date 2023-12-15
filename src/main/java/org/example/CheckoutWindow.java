package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckoutWindow {

    private JFrame jFrame;
    private JPanel checkoutWindow;
    private JList checkoutJList;
    private JButton makePurchaseButton;
    private JButton goBackToShopButton;
    private JLabel costLabel;
    private JButton logOutButton;
    private JLabel userLabel;
    private JButton removeFromCartButton;
    private JButton reviewOrdersButton;
    DefaultListModel listModel = new DefaultListModel<>();
    private int finalCost;

    // Creates the CheckoutWindow where the user can go through their cart before trying to purchase any items.
    public CheckoutWindow(Customer currentUser , ProductManager productManager
            , ArrayList<Product> productsToCheckoutArrayList, Admin admin) {
        checkoutJList.setModel(listModel);
        jFrame = new JFrame("Checkout");
        jFrame.setContentPane(checkoutWindow);
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        checkoutListRefresh(productsToCheckoutArrayList);
        userLabel.setText("Current user: " + currentUser.getUsername());

        // Takes you back to the Storefront if pressed.
        goBackToShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPanel customerPanel = new CustomerPanel(productManager, currentUser, admin);
                jFrame.dispose();
            }
        });
        // Takes you to the card related part of the checkout if pressed, here the user can register cards or
        // verify already registered cards to be used when completing their purchase of items.
        makePurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardWindow cardWindow = new CardWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        //Takes you back to the login screen if pressed.
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginPanel = new LoginPanel(productManager, admin);
                jFrame.dispose();
            }
        });
        // Removes the selected JList index from the cart if pressed.
        removeFromCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int removeIndex = checkoutJList.getSelectedIndex();
                if(removeIndex > -1){
                    productsToCheckoutArrayList.remove(removeIndex);
                    checkoutListRefresh(productsToCheckoutArrayList);
                }
            }
        });
        // Takes you to the menu where you can overview past purchases and refund them if pressed.
        reviewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PastPurchasesWindow past = new PastPurchasesWindow(currentUser, productManager
                        , productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
    }
    // Used to visualize the list and to refresh it if any changes happen.
    private void checkoutListRefresh(ArrayList<Product> productsToCheckoutArrayList){
        finalCost = 0;
        for(Product p : productsToCheckoutArrayList){
            finalCost += p.getCost();
        }
        costLabel.setText("Final cost is: " + finalCost + " buckaroos.");
        listModel.removeAllElements();
        for(Product p : productsToCheckoutArrayList){
            listModel.addElement(p.getName() + "  Cost: " +  p.getCost());
        }
    }
}
