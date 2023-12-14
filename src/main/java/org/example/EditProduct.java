package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JButton applyButton;
    private JButton cancelButton;
    private JLabel messageLabel;
    private JPanel mainPanel;
    private DefaultListModel productListModel = new DefaultListModel();
    private Product selectedProduct;

    public EditProduct(ProductManager productManager){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setSize(700, 800);
        jFrame.setLocation(60, 60);
        jFrame.setContentPane(mainPanel);
        jFrame.setVisible(true);
        productJlist.setModel(productListModel);;
        for (int i = 0; i < productManager.productArrayList.size(); i++) {
            productListModel.addElement(productManager.productArrayList.get(i).GetDescription());
        }
        applyButton.setEnabled(false);
        deletProductButton.setEnabled(false);
        editProductButton.setEnabled(false);

        deletProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = productJlist.getSelectedIndex();
                if (confirmCheckBox.isSelected()){

                    productManager.productArrayList.remove(index);
                    productListModel.removeElementAt(index);
                    messageLabel.setText(productNameField.getText()+" has been deleted successfully!");
                    confirmCheckBox.setSelected(false);
                    editProductButton.setEnabled(false);
                    deletProductButton.setEnabled(false);
                    productNameField.setText("");
                    productPriceField.setText("");
                    productCategoryField.setText("");
                    productQuantityField.setText("");
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
                editProductButton.setEnabled(false);
                applyButton.setEnabled(true);
            }
        });
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!productNameField.getText().isEmpty()&&!productPriceField.getText().isEmpty()&&!productCategoryField.getText().isEmpty()&&!productQuantityField.getText().isEmpty()){
                    try {
                        selectedProduct.setName(productNameField.getText());
                        selectedProduct.setCost(Integer.parseInt(productPriceField.getText()));
                        selectedProduct.setCategory(productCategoryField.getText());
                        selectedProduct.setQuantity(Integer.parseInt(productQuantityField.getText()));
//                        EmployeePanel employeePanel = new EmployeePanel(productManager, admin, currentEmployee);
//                        Save()....VÄNTAR PÅ AXEL.
                        messageLabel.setText(productNameField.getText()+" has been edited successfully!");
                        //Inte säker hur man uppdaterar JListen.
                        //Här ska confirmCheckBox bli tom igen
                        productNameField.setText("");
                        productPriceField.setText("");
                        productCategoryField.setText("");
                        productQuantityField.setText("");
                        applyButton.setEnabled(false);


                    } catch (Exception exception){
                        Error error = new Error("Wrong input", "Please insert correct input type!");

                    }
                } else {
                    Error error = new Error("Empty fields!", "Please make sure that all fields are filled!");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
            }
        });
        productJlist.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                deletProductButton.setEnabled(true);
                editProductButton.setEnabled(true);
            }
        });
        confirmCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (confirmCheckBox.isSelected()){
                    deletProductButton.setEnabled(true);
                    editProductButton.setEnabled(false);
                } else if (!confirmCheckBox.isSelected()){
                    deletProductButton.setEnabled(false);
//                    editProductButton.setEnabled(false);
                }

            }
        });
    }
}
