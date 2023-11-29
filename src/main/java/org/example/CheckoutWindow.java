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
    DefaultListModel listModel = new DefaultListModel<>();
    private String name;
    int finalCost;
    ArrayList<Order> orderList = new ArrayList<>();
    ArrayList<String> transferList = new ArrayList<>();



    public CheckoutWindow(Customer currentUser , ProductManager productManager, ArrayList<Product> productsToCheckoutArrayList) {
        for(Product p : productsToCheckoutArrayList){
            finalCost += p.getCost();
        }
        checkoutJList.setModel(listModel);
        jFrame = new JFrame("Checkout");
        jFrame.setContentPane(checkoutWindow);
        jFrame.setSize(1000, 1000);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkoutListRefresh(productsToCheckoutArrayList);
        costLabel.setText("Final cost is: " + finalCost + " buckaroos.");

        goBackToShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPanel customerPanel = new CustomerPanel(productManager, currentUser);
                jFrame.dispose();
            }
        });
        makePurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Product pr : productsToCheckoutArrayList) {
                    if (currentUser.getBalance() >= finalCost && pr.checkQuantity(pr.getQuantity())) {
                        name = currentUser.getUsername();
                        for (Product p : productsToCheckoutArrayList) {
                            transferList.add(p.getName());
                        }
                        orderList.add(new Order(name, finalCost, transferList));
                        currentUser.setBalance(currentUser.getBalance() - finalCost);
                        jFrame.dispose();
                    } else {
                        costLabel.setText("You don't have enough money to finalize the purchase" +
                                " or there aren't enough items in stock.");
                    }
                }
            }
        });
    }
    private void checkoutListRefresh(ArrayList<Product> productsToCheckoutArrayList){
        listModel.removeAllElements();
        for(Product p : productsToCheckoutArrayList){
            listModel.addElement(p.getName() + "  Cost: " +  p.getCost());
        }
    }
}
