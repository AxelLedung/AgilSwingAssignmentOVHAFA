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
                if (index >= 0) {
                    productManager.productArrayList.get(index).getReviewArrayList().add(new Review(textArea1.getText()));
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
    }
    /*private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile, true))) {
                writer.write("Product: " + jProductList.getText() + "\n");
                writer.write("Review: ");
                writer.write(textArea1.getText() + "\n");
                writer.write("Rating: ");
                appendCheckBoxState(writer, a1CheckBox);
                appendCheckBoxState(writer, a2CheckBox);
                appendCheckBoxState(writer, a3CheckBox);
                appendCheckBoxState(writer, a4CheckBox);
                appendCheckBoxState(writer, a5CheckBox);
                writer.write("\n");
                JOptionPane.showMessageDialog(this, "Review saved");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }*/
    private void appendCheckBoxState(BufferedWriter writer, JCheckBox checkBox) throws IOException {
        if (checkBox.isSelected()) {
            writer.write(checkBox.getText() + " ");
        }
    }
}

