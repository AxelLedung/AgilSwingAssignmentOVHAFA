package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminPanel {
    ArrayList<User> UserList = new ArrayList<>();
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

        for (Employee n: Employeelist) {
            listModel.addElement(n.getDescription());
        }
        for (Customer n: CustomerList) {
            listModel2.addElement(n.getDescription());
        }


        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Text1 = Field1.getText();
                String Text2 = Field2.getText();
                if (!Field1.getText().isEmpty() && !Field2.getText().isEmpty()) {
                    Employeelist.add(new Employee(Text1, Text2));
                } else {
                    Field1.setText("");
                    Field2.setText("");
                    Message.setText("Cannot be empty!");
                }
                Employeelist.add(new Employee(Text1, Text2));
                for (Employee n: Employeelist){
                    if (n.getUsername().equals(Text1)) {
                        listModel.addElement(n.getDescription());
                        Field1.setText("");
                        Field2.setText("");
                        Message.setText("");
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
                    Employeelist.remove(index);
                }
            }
        });
        removeCbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list2.getSelectedIndex();
                if (index >= 0) {
                    listModel2.remove(index);
                    CustomerList.remove(index);
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
