package org.example;

import javax.swing.*;

public class CustomerPanel {
    private JPanel jPanel;
    private JList jProductList;
    private JButton checkoutButton;
    private JButton backButton;
    private JButton addProductButton;
    private DefaultListModel defaultListModel = new DefaultListModel();
    public CustomerPanel(ProductManager productManager) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
        jProductList.setModel(defaultListModel);

        for (int i = 0; i < productManager.productArrayList.size(); i++) {
            defaultListModel.addElement(productManager.productArrayList.get(i).GetDescription());
        }
    }

}
