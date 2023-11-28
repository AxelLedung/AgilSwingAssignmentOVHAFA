package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class EmployeePanel {
    private JPanel jPanel;
    private JList jProductList;
    private JButton createProduct;
    private JButton editProduct;
    private JButton removeProduct;

    public EmployeePanel(JList jProductList, JButton createProduct, JButton editProduct, JButton removeProduct) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
    }
}
