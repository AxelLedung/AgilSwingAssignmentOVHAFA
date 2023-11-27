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

    DefaultListModel listModel = new DefaultListModel<>();

    public CheckoutWindow(ArrayList<Customer> customerList, ArrayList<Product> checkoutList, ArrayList<Product> productList) {
        checkoutJList.setModel(listModel);
        jFrame = new JFrame("Checkout");
        jFrame.setContentPane(checkoutWindow);
        jFrame.setSize(1000, 1000);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkoutListRefresh(checkoutList);

        goBackToShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopWindow shopWindow = new ShopWindow(customerList, productList);
                jFrame.dispose();
            }
        });
    }
    public void checkoutListRefresh(ArrayList<Product> checkoutList){
        listModel.removeAllElements();
        for(Product p : checkoutList){
            listModel.addElement(p.getProductName());
        }
    }
}
