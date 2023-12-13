package org.example;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CustomerPanel {
    private JPanel jPanel;
    private JList jProductList;
    private JButton checkoutButton;
    private JButton backButton;
    private JButton addProductButton;
    private JList jCheckoutList;
    private JButton reviewButton;
    private JTextArea jReviewText;
    private JLabel jCategoryLabel;
    private JLabel jCostLabel;
    private JLabel jNameLabel;
    private DefaultListModel productListModel = new DefaultListModel();
    private DefaultListModel checkoutListModel = new DefaultListModel();
    private Customer currentUser;
    public ArrayList<Product> productsToCheckoutArrayList = new ArrayList<Product>();
    public CustomerPanel(ProductManager productManager, Customer currentUser, Admin admin) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(1000, 1000);
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
                LoginPanel loginPanel = new LoginPanel(productManager, admin);
                jFrame.dispose();
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
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
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewPanel reviewPanel = new ReviewPanel(productManager, currentUser, admin);
                jFrame.dispose();
            }
        });

        jProductList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = jProductList.getSelectedIndex();
                jNameLabel.setText(productManager.productArrayList.get(index).getName());
                jCategoryLabel.setText(productManager.productArrayList.get(index).getCategory());
                jCostLabel.setText(Integer.toString(productManager.productArrayList.get(index).getCost()));
                String reviewString = "";
                for (Review r : productManager.productArrayList.get(index).getReviewArrayList()) {
                    reviewString = reviewString + "\n\r" + r.getCustomer().getUsername() + " tycker:" + "\n\r" + r.getText() + " Rating: " + r.getRating();
                }
                jReviewText.setText(reviewString);
            }
        });
    }
}
