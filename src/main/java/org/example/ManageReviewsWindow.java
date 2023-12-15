package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageReviewsWindow {
    private JPanel mainPanel;
    private JList productList;
    private JList reviewList;
    private JButton deleteButton;
    private JCheckBox confirmCheckBox;
    private Product selectedProduct;
    private DefaultListModel productListModel = new DefaultListModel();
    private DefaultListModel reviewListModel = new DefaultListModel();

    public ManageReviewsWindow(ProductManager productManager) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(600, 600);
        jFrame.setContentPane(mainPanel);
        jFrame.setVisible(true);
        productList.setModel(productListModel);
        reviewList.setModel(reviewListModel);
        for (int i = 0; i < productManager.productArrayList.size(); i++) {
            productListModel.addElement(productManager.productArrayList.get(i).GetDescription());
        }
        productList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = productList.getSelectedIndex();
                selectedProduct = productManager.productArrayList.get(index);
                for (int i = 0; i < selectedProduct.getReviewArrayList().size(); i++) {
                    reviewListModel.addElement(selectedProduct.getReviewArrayList().get(i));
                }
            }
        });
    }
}
