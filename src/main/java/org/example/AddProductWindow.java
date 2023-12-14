package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductWindow {
    private JPanel panel1;
    private JTextField productNameField;
    private JTextField productQuantityField;
    private JTextField productCategoryField;
    private JTextField productPriceField;
    private JButton createProductButton;
    private JButton cancelButton;
    private JLabel messageLabel;

    public AddProductWindow(ProductManager productManager){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setSize(300, 400);
        jFrame.setLocation(60, 60);
        jFrame.setContentPane(panel1);
        jFrame.setVisible(true);
        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String newProductName = productNameField.getText();
                    int newProductCost = Integer.parseInt(productPriceField.getText());
                    String newProductCategory = productCategoryField.getText();
                    int newProductQuantity = Integer.parseInt(productQuantityField.getText());
                    if (!newProductName.isEmpty()&&newProductQuantity>=0&&!newProductCategory.isEmpty()&&newProductCost>=1){
                        //Save() ...VÄNTAR PÅ AXEL
                        productManager.productArrayList.add(new Product(newProductName, newProductCost, newProductCategory,newProductQuantity));
                        //Här nedan töms fieldsen efter man har sparat den nya produkten.
                        productNameField.setText("");
                        productPriceField.setText("");
                        productCategoryField.setText("");
                        productQuantityField.setText("");
                        messageLabel.setText(newProductName+" has been created successfully!");

                    }else {
                        Error error = new Error("Empty fields", "Please make sure that you filled all the fields and try again!");

                    }
                } catch (Exception exception){
                    Error error = new Error("Invalid input/s", "Please type in numbers into Cost/Quantity fields");

                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
            }
        });
    }
}
