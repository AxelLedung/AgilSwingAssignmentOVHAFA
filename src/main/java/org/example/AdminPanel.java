package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPanel {
    Admin admin = new Admin();
    private JList list2;
    private JButton AddButton;
    private JTextField Field1;
    private JPanel AdminPanel;
    private JButton RemoveButton;
    private JTextField Field2;
    private JButton removeCbutton;
    private JList list1;
    private JButton logOutButton;
    private JLabel Message;
    private DefaultListModel listModel = new DefaultListModel();
    private DefaultListModel listModel2 = new DefaultListModel<>();
    JFrame jframe = new JFrame();
    public AdminPanel(){
        jframe.setSize(500,500);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setContentPane(AdminPanel);

        list2.setModel(listModel);
        list1.setModel(listModel2);

        for (User user: admin.UserList) {
            if (user instanceof Employee) {
                listModel.addElement(user.getDescription());
            }
        }
        for (User user: admin.UserList) {
            listModel2.addElement(user.getDescription());
        }


        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Text1 = Field1.getText();
                String Text2 = Field2.getText();
                if (!Field1.getText().isEmpty() && !Field2.getText().isEmpty()) {
                    admin.AddEmloyee(Text1, Text2);
                } else {
                    Field1.setText("");
                    Field2.setText("");
                    Message.setText("Cannot be empty!");
                }

                for (User user: admin.UserList) {
                    if (user instanceof Employee) {
                        if (user.getUsername().equals(Text1)) {
                            listModel.addElement(user.getDescription());
                            Field1.setText("");
                            Field2.setText("");
                            Message.setText("");
                        }
                    }
                }
            }
        });
        RemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list2.getSelectedIndex();
                if (index >= 0) {
                    listModel.remove(index);
                    admin.RemoveEmployee(index);
                }
            }
        });
        removeCbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list2.getSelectedIndex();
                if (index >= 0) {
                    listModel2.remove(index);
                    admin.RemoveCustomer(index);
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
