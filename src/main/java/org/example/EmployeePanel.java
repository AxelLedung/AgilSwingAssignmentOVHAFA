package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePanel {

    private JButton createProductBtton;
    private JButton editProductButton;
    private JButton removeProductButton;
    private JPanel employeePanel;
    private JLabel currentEmployeeLabel;
    private JList productsJlist;
    private JButton logoutButton;
    private JButton confirmChangesButton;
    private JTextField selctedProductQuantity;
    private JTextField selectedProductCategory;
    private JTextField selectedProductCost;
    private JTextField selectedProductName;
    private JLabel messageLabel;
    private Employee currentEmployee;
    private Product selectedProduct;
    private DefaultListModel productListModel = new DefaultListModel();


    public EmployeePanel(ProductManager productManager, Admin admin,Employee currentEmployee) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(600, 600);
        jFrame.setContentPane(employeePanel);
        jFrame.setVisible(true);
        productsJlist.setModel(productListModel);;
        for (int i = 0; i < productManager.productArrayList.size(); i++) {
            productListModel.addElement(productManager.productArrayList.get(i).GetDescription());
        }
        currentEmployeeLabel.setText("Welcome "+currentEmployee.getUsername());
        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int index = productsJlist.getSelectedIndex();
                productManager.productArrayList.remove(index);
                productListModel.removeElementAt(index);
                Shop.Save(productManager, admin);
            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = productsJlist.getSelectedIndex();
                selectedProduct = productManager.productArrayList.get(index);
                selectedProductName.setText( selectedProduct.getName());
                selectedProductCost.setText(""+selectedProduct.getCost());
                selectedProductCategory.setText(selectedProduct.getCategory());
                selctedProductQuantity.setText(""+ selectedProduct.getQuantity());
            }
        });
        confirmChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectedProduct.setName(selectedProductName.getText());
                    selectedProduct.setCost(Integer.parseInt(selectedProductCost.getText()));
                    selectedProduct.setCategory(selectedProductCategory.getText());
                    selectedProduct.setQuantity(Integer.parseInt(selctedProductQuantity.getText()));
                    Shop.Save(productManager, admin);
                    EmployeePanel employeePanel = new EmployeePanel(productManager, admin, currentEmployee);
                    jFrame.setVisible(false);
                } catch (Exception exception){
                    messageLabel.setText("Something went wrong, please try again!");
                }
            }
        });
        createProductBtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String newProductName = selectedProductName.getText();
                    int newProductCost = Integer.parseInt(selectedProductCost.getText());//Maby try catch
                    String newProductCategory = selectedProductCategory.getText();
                    int newProductQuantity = Integer.parseInt(selctedProductQuantity.getText());
                    if (!newProductName.isEmpty()&&newProductQuantity>=0&&!newProductCategory.isEmpty()&&newProductCost>=1){
                        System.out.println("#1" + productManager.productArrayList);
                        productManager.productArrayList.add(new Product(newProductName, newProductCategory, newProductCost, newProductQuantity));
                        EmployeePanel employeePanel = new EmployeePanel(productManager, admin, currentEmployee);
                        System.out.println("#2" + productManager.productArrayList);
                        Shop.Save(productManager, admin);
                        jFrame.setVisible(false);
                    }else {
                        messageLabel.setText("Please make sure that you filled all the fields and try again!");
                    }
                } catch (Exception exception){
                    messageLabel.setText("Please type in numbers into Cost/Quantity fields");
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginPanel = new LoginPanel(productManager, admin);
                jFrame.setVisible(false);
            }
        });
    }
}
