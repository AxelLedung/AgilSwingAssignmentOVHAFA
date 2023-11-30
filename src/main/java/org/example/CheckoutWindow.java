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
    DefaultListModel listModel = new DefaultListModel<>();
    private String name;
    int finalCost;
    ArrayList<Order> orderList = new ArrayList<>();
    ArrayList<String> transferList = new ArrayList<>();



    public CheckoutWindow(Customer currentUser , ProductManager productManager, ArrayList<Product> productsToCheckoutArrayList, Admin admin) {
        checkoutJList.setModel(listModel);
        jFrame = new JFrame("Checkout");
        jFrame.setContentPane(checkoutWindow);
        jFrame.setSize(1000, 1000);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkoutListRefresh(productsToCheckoutArrayList);
        userLabel.setText("Current user: " + currentUser.getUsername());

        goBackToShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPanel customerPanel = new CustomerPanel(productManager, currentUser, admin);
                jFrame.dispose();
            }
        });
        makePurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean purchased = false;
                for (Product pr : productsToCheckoutArrayList) {
                    if (currentUser.getBalance() >= finalCost && pr.checkQuantity(pr.getQuantity())) {
                       purchased = true;
                    }
                }
                if(purchased){
                    name = currentUser.getUsername();
                    for (Product p : productsToCheckoutArrayList) {
                        transferList.add(p.getName());
                    }
                    orderList.add(new Order(name, finalCost, transferList));
                    currentUser.setBalance(currentUser.getBalance() - finalCost);
                    CustomerPanel customerPanel = new CustomerPanel(productManager, currentUser, admin);
                    jFrame.dispose();
                }
                else {
                    costLabel.setText("You don't have enough money to finalize the purchase" +
                            " or there aren't enough items in stock.");
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginPanel = new LoginPanel(productManager, admin);
                jFrame.dispose();
            }
        });
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
    }
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
