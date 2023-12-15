package org.example;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;

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
    private JButton sortNameButton;
    private JButton sortCategoryButton;
    private JButton sortPriceButton;
    private JPanel productInformationPanel;
    private JButton searchButton;
    private JPanel showProductDisplay;
    private JPanel productCheckoutPanel;
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
        productInformationPanel.setVisible(false);
        DisplayProductsByName(productManager);
        //Returns the user back to Login window
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginPanel = new LoginPanel(productManager, admin);
                jFrame.dispose();
            }
        });
        //Send all the chosen products to the checkout
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        //Add a Product
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
        //Add a review
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewPanel reviewPanel = new ReviewPanel(productManager, currentUser, admin);
                jFrame.dispose();
            }
        });
        //Displays the Product Information Panel
        //Updates the Product information to show the chosen products information
        jProductList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                productInformationPanel.setVisible(true);
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
        sortPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayProductsByPrice(productManager);
            }
        });
        sortCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayProductsByCategory(productManager);
            }
        });
        sortNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayProductsByName(productManager);
            }
        });
    }
    public void DisplayProductsByCategory(ProductManager productManager) {
        productListModel.clear();
        ArrayList<Product> sortedProductList = productManager.productArrayList;
        sortedProductList.sort((p1, p2) -> p1.getCategory().compareTo(p2.getCategory()));
        for (int i = 0; i < sortedProductList.size(); i++) {
            productListModel.addElement(sortedProductList.get(i).GetDescription());
        }
    }
    public void DisplayProductsByPrice(ProductManager productManager) {
        productListModel.clear();
        ArrayList<Product> sortedProductList = productManager.productArrayList;
        sortedProductList.sort((p1, p2) -> p1.getCost() - p2.getCost());
        for (int i = 0; i < sortedProductList.size(); i++) {
            productListModel.addElement(sortedProductList.get(i).GetDescription());
        }
    }
    public void DisplayProductsByName(ProductManager productManager) {
        productListModel.clear();
        ArrayList<Product> sortedProductList = productManager.productArrayList;
        sortedProductList.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        for (int i = 0; i < sortedProductList.size(); i++) {
            productListModel.addElement(sortedProductList.get(i).GetDescription());
        }
    }
}
