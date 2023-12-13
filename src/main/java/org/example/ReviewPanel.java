package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReviewPanel extends JFrame {
    private JButton submitButton;
    private JTextArea textArea1;
    private JList jProductList;
    private JPanel Panel1;
    private JCheckBox a4CheckBox;
    private JCheckBox a3CheckBox;
    private JCheckBox a2CheckBox;
    private JCheckBox a1CheckBox;
    private JCheckBox a5CheckBox;
    private JLabel rvLabel2;
    private JButton CustomerPanelButton;
    private DefaultListModel productListModel = new DefaultListModel();
    private int selectedRating = -1;
    // standardvärde när ingen rating är vald
    public ReviewPanel(ProductManager productManager, Customer currentUser, Admin admin) {
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(Panel1);
        jFrame.setTitle("ReviewPanel");
        jFrame. setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jProductList.setModel(productListModel);
        for (int i = 0; i < productManager.productArrayList.size(); i++) {
            productListModel.addElement(productManager.productArrayList.get(i).GetDescription());
        }
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jProductList.getSelectedIndex();
                if (index >= 0&& selectedRating != -1) {
                    productManager.productArrayList.get(index).getReviewArrayList().add(new Review(textArea1.getText(), currentUser, selectedRating));
                //checkar nu för selected rating
                }
            }
        });
        CustomerPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              CustomerPanel customerPanel = new CustomerPanel(productManager, currentUser, admin);
                jFrame.setVisible(false);
            }
        });
        a1CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRating = 1;
            }
        });
        a2CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRating = 2;
            }
        });
        a3CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRating = 3;
            }
        });
        a4CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRating = 4;
            }
        });
        a5CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRating = 5;
            }
        });
    }

    private ActionListener createRatingActionListener(final int rating) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sätt vald rating när en knapp är tryckt
                selectedRating = rating;
            }
        };
    }

    private void appendCheckBoxState(BufferedWriter writer, JCheckBox checkBox) throws IOException {
        if (checkBox.isSelected()) {
            writer.write(checkBox.getText() + " ");
        }
    }
}
