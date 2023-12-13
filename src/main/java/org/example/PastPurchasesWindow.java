package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PastPurchasesWindow {
    private JList orderJList;
    private JButton refundOrderButton;
    private JButton goBackToCheckoutButton;
    private JPanel orderMenu;
    DefaultListModel listModel = new DefaultListModel<>();
    JFrame jFrame;

    public PastPurchasesWindow(Customer currentUser, ProductManager productManager
            , ArrayList<Product> productsToCheckoutArrayList, Admin admin){
        orderJList.setModel(listModel);
        jFrame = new JFrame("Past orders");
        jFrame.setContentPane(orderMenu);
        jFrame.setSize(750, 750);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        orderListRefresh(productManager.orderList, currentUser);
        refundOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object element = orderJList.getModel().getElementAt(orderJList.getSelectedIndex()).toString().replace(" ","").replace(",","");
                for(Order o : productManager.getOrders()){
                    Object findThing = o.getId() + o.getCustomerName() + o.getOrderSum() + o.getOrderList().toString().replace(" ","").replace("," ,"").replace("[", "").replace("]","");
                    if(findThing.equals(element)){
                        productManager.refundList.add(o);
                        currentUser.setBalance(currentUser.getBalance() + o.getSum());
                        for(String or : o.getOrderList()){
                            for(Product p : productManager.productArrayList){
                                if(or.toString().equals(p.getName())){
                                    p.setQuantity(p.getQuantity() + 1);
                                }
                            }
                        }
                        productManager.orderList.remove(o.getId());
                        break;
                    }
                }
                orderListRefresh(productManager.orderList, currentUser);
            }
        });
        goBackToCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager
                        , productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
    }

    private void orderListRefresh(ArrayList<Order> orderList, Customer currentUser){
        listModel.removeAllElements();
        for(Order o : orderList){
            if(o.getCustomerName().equals(currentUser.getUsername())){
                listModel.addElement(o.getDescription());
            }
        }
    }
}
