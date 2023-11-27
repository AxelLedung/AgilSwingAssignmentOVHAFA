package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShopWindow {
    private JList productJList;
    private JPanel shopWindow;
    private JButton addToCartButton;
    private JButton goToCheckoutButton;
    private JList checkoutJList;
    private JFrame jFrame;
    private DefaultListModel listModel = new DefaultListModel<>();
    private DefaultListModel listModel2 = new DefaultListModel<>();
    ArrayList<Product> checkoutList = new ArrayList<>();
    ArrayList<Customer> customerList;

    public ShopWindow(ArrayList<Customer> customerList, ArrayList<Product> productList){
        this.customerList = customerList;
        productJList.setModel(listModel);
        checkoutJList.setModel(listModel2);
        jFrame = new JFrame("Shop");
        jFrame.setContentPane(shopWindow);
        jFrame.setSize(1000,1000);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        productListRefresh(productList);

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexToAdd = productJList.getSelectedIndex();
                for(Product p : productList) {
                    if(p.getId()-1 == indexToAdd){
                        checkoutList.add(p);
                    }
                }
                productListRefresh(productList);
                checkoutListRefresh(checkoutList);
            }
        });

        goToCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(customerList, checkoutList, productList);
                jFrame.dispose();
            }
        });
    }

    public void productListRefresh(ArrayList<Product> productList){
        listModel.removeAllElements();
        for(Product p : productList){
            listModel.addElement(p.getProductName());
        }
    }

    public void checkoutListRefresh(ArrayList<Product> checkoutList){
        listModel2.removeAllElements();
        for(Product p : checkoutList) {
                listModel2.addElement(p.getProductName());
            }
        }
    }
