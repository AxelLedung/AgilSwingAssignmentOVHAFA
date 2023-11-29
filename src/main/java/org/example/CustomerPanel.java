package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerPanel {
    private JPanel jPanel;
    private JList jProductList;
    private JButton checkoutButton;
    private JButton backButton;
    private JButton addProductButton;
    private JList jCheckoutList;
    private DefaultListModel productListModel = new DefaultListModel();
    private DefaultListModel checkoutListModel = new DefaultListModel();
    public ArrayList<Product> productsToCheckoutArrayList = new ArrayList<Product>();
    public CustomerPanel(ProductManager productManager) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
        jProductList.setModel(productListModel);
        jCheckoutList.setModel(checkoutListModel);

        for (int i = 0; i < productManager.productArrayList.size(); i++) {
            productListModel.addElement(productManager.productArrayList.get(i).GetDescription());
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Back to Login screen
                LoginPanel loginPanel = new LoginPanel();
                jFrame.setVisible(false);
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Create a checkout window and send: productsToCheckoutArrayList to Checkout Window
            }
        });
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jProductList.getSelectedIndex();
                if (index >= 0) {
                    productsToCheckoutArrayList.add(productManager.productArrayList.get(index));
                    checkoutListModel.addElement(productManager.productArrayList.get(index).GetDescription());
                }
            }
        });
    }

}
