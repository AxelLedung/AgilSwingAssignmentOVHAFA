package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProduct {
    private JTextField productNameField;
    private JTextField productQuantityField;
    private JTextField productCategoryField;
    private JTextField productPriceField;
    private JList productJlist;
    private JButton deletProductButton;
    private JButton editProductButton;
    private JCheckBox confirmCheckBox;
    private JPanel editPanel;
    private JButton applyEditingsButton;
    private DefaultListModel productListModel = new DefaultListModel();
    private Product selectedProduct;

    public EditProduct(ProductManager productManager){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setSize(600, 800);
        jFrame.setLocation(60, 60);
        jFrame.setContentPane(editPanel);
        jFrame.setVisible(true);
        productJlist.setModel(productListModel);;
        for (int i = 0; i < productManager.productArrayList.size(); i++) {
            productListModel.addElement(productManager.productArrayList.get(i).GetDescription());
        }
        deletProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (confirmCheckBox.isFocusPainted()){
                    int index = productJlist.getSelectedIndex();
                    productManager.productArrayList.remove(index);
                    productListModel.removeElementAt(index);
                }
            }
        });
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = productJlist.getSelectedIndex();
                selectedProduct = productManager.productArrayList.get(index);
                productNameField.setText( selectedProduct.getName());
                productPriceField.setText(""+selectedProduct.getCost());
                productCategoryField.setText(selectedProduct.getCategory());
                productQuantityField.setText(""+ selectedProduct.getQuantity());
            }
        });
        applyEditingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!productNameField.getText().isEmpty()&&!productPriceField.getText().isEmpty()&&!productCategoryField.getText().isEmpty()&&!productQuantityField.getText().isEmpty()){
                    try {
                        selectedProduct.setName(productNameField.getText());
                        selectedProduct.setCost(Integer.parseInt(productPriceField.getText()));
                        selectedProduct.setCategory(productCategoryField.getText());
                        selectedProduct.setQuantity(Integer.parseInt(productQuantityField.getText()));
//                        EmployeePanel employeePanel = new EmployeePanel(productManager, admin, currentEmployee);
                        //Save()....VÄNTAR PÅ AXEL.
                        jFrame.setVisible(false);

                    } catch (Exception exception){
                        Error error = new Error("Wrong input", "Please insert correct input type!");

                    }
                } else {
                    Error error = new Error("Empty fields!", "Please make sure that all fields are filled!");
                }
            }
        });
    }
}
