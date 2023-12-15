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
    private JButton manageReviewsButton;
    private Employee currentEmployee;
    private Product selectedProduct;
    private DefaultListModel productListModel = new DefaultListModel();


    public EmployeePanel(ProductManager productManager, Admin admin,Employee currentEmployee) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(600, 600);
        jFrame.setContentPane(employeePanel);
        jFrame.setVisible(true);
//        productsJlist.setModel(productListModel);;
//        for (int i = 0; i < productManager.productArrayList.size(); i++) {
//            productListModel.addElement(productManager.productArrayList.get(i).GetDescription());
//        }
        currentEmployeeLabel.setText("Welcome "+currentEmployee.getUsername());
        /*removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = productsJlist.getSelectedIndex();
                productManager.productArrayList.remove(index);
                productListModel.removeElementAt(index);
                Shop.Save(productManager, admin);
            }
        });*/
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditProduct editProduct = new EditProduct(productManager, admin);
            }
        });
        /*manageReviewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });*/
        createProductBtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductWindow addProductWindow=new AddProductWindow(productManager, admin);
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
