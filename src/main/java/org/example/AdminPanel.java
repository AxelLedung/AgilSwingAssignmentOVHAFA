package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminPanel {
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
    private JList list3Products;
    private JTextField Field3Discount;
    private JButton DiscountButton;
    private JLabel discountMessage;
    private DefaultListModel listModel = new DefaultListModel();
    private DefaultListModel listModel2 = new DefaultListModel<>();
    private DefaultListModel listmodel3 = new DefaultListModel();
    JFrame jframe = new JFrame();
    public AdminPanel(Admin admin, ProductManager productManager) {
        jframe.setSize(1000, 500);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setContentPane(AdminPanel);
        list2.setModel(listModel);
        list1.setModel(listModel2);
        list3Products.setModel(listmodel3);
        //JScrollPane scrollPaneList1 = new JScrollPane(list1);
        //scrollPaneList1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //ScrollpaneCustomers.setViewportView(scrollPaneList1);
        jframe.setVisible(true);

        for (Employee user : admin.EmployeeList) {
            listModel.addElement(user.getDescription());
        }
        for (Customer user : admin.CustomerList) {
            listModel2.addElement(user.getDescription());
        }
        for (Product product : productManager.productArrayList) {
            listmodel3.addElement(product.getName() + "  " + product.getCost());
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
                for (Employee user : admin.EmployeeList) {
                    if (user.getUsername().equals(Text1)) {
                        listModel.addElement(user.getDescription());
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
                    admin.RemoveEmployee(index);
                }
            }
        });
        removeCbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list1.getSelectedIndex();
                if (index >= 0) {
                    listModel2.remove(index);
                    admin.RemoveCustomer(index);
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginpanel = new LoginPanel(productManager, admin);
                jframe.dispose();
            }
        });


        // Gjorde en discount funktion som ändrar priset i produktlistan
        // Vi kanske skulle behöva en label i shop fönstret som säger vilka produkter som har rabatter
        DiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double price;
                int currentprice;
                try {
                    double discount = Integer.parseInt(Field3Discount.getText());
                    if (discount > 0 && discount <= 100) {
                        try {
                            discountMessage.setText("");
                            int index = list3Products.getSelectedIndex();
                            Product selectedProduct = (Product) productManager.productArrayList.get(index);
                            for (Product product : productManager.productArrayList) {
                                if (selectedProduct.equals(product)) {
                                    currentprice = product.getCost();
                                    double procent = discount / 100;
                                    price = currentprice - (currentprice * procent);
                                    long roundprice = Math.round(price);
                                    int sendprice = (int) roundprice;
                                    product.setCost(sendprice);
                                    RefreshProductList(productManager);
                                    discountMessage.setText(product.getName() + " now has " + Math.round(discount) + "% discount");
                                    Field3Discount.setText("");
                                }
                            }
                        } catch (Exception i) {
                            discountMessage.setText("Please select a product...");
                            Field3Discount.setText("");
                        }
                    } else {
                        discountMessage.setText("Please enter a value between 0-100...");
                        Field3Discount.setText("");
                    }
                } catch (NumberFormatException n) {
                    discountMessage.setText("Please type a value");
                }


            }
        });
        Field1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AddButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

        Field2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    AddButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

    } private void RefreshProductList(ProductManager productManager) {
        listmodel3.removeAllElements();
        for (Product product : productManager.productArrayList) {
            listmodel3.addElement(product.getName() + "  " + product.getCost());
        }
    }
}
