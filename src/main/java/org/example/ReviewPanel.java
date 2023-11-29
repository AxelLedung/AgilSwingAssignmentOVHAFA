package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReviewPanel extends JFrame {
    private JButton tempProductButton;
    private JLabel rvLabel2;
    private JCheckBox a5CheckBox;
    private JCheckBox a4CheckBox;
    private JCheckBox a3CheckBox;
    private JCheckBox a2CheckBox;
    private JCheckBox a1CheckBox;
    private JButton submitButton;
    private JTextArea textArea1;

    public ReviewPanel () {
        setContentPane(ReviewPanel1);
        setTitle("ReviewPanel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(textArea1.getText());

                JOptionPane.showMessageDialog(this, "Review saved");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReviewPanel());
    }
}






