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

    // Creates the "PastPurchasesWindow" where the user can view their past orders
    public PastPurchasesWindow(Customer currentUser, ProductManager productManager
            , ArrayList<Product> productsToCheckoutArrayList, Admin admin){
        orderJList.setModel(listModel);
        jFrame = new JFrame("Past orders");
        jFrame.setContentPane(orderMenu);
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        orderListRefresh(productManager.orderArrayList, currentUser);
        refundOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Creates a String using the object found at the selected JList index that will later
                // be used for comparison.
                Object element = orderJList.getModel().getElementAt(orderJList.getSelectedIndex()).toString()
                        .replace(" ","").replace(",","");
                for(Order o : productManager.orderArrayList){
                    // Creates a String using the object found at the current OrderList index that will later
                    // be used for comparison.
                    Object findThing = o.getId() + o.getCustomerName() + o.getOrderSum() + o.getOrderList().toString()
                            .replace(" ","").replace("," ,"")
                            .replace("[", "").replace("]","");
                    // Compares the two Strings and if they are the same the order will be added to a list of refunded
                    // orders, the money spent on the items will be returned to the users and the items will be added
                    // back into stock. If they aren't the same then nothing happens and the program just moves on.
                    if(findThing.equals(element)){
                        productManager.refundArrayList.add(o);
                        currentUser.setBalance(currentUser.getBalance() + o.getSum());
                        for(String or : o.getOrderList()){
                            for(Product p : productManager.productArrayList){
                                if(or.equals(p.getName())){
                                    p.setQuantity(p.getQuantity() + 1);
                                }
                            }
                        }
                        productManager.orderArrayList.remove(o.getId());
                        break;
                    }
                }
                orderListRefresh(productManager.orderArrayList, currentUser);
            }
        });
        // Takes you back to the checkout if pressed.
        goBackToCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager
                        , productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
    }
    // Used to visualize the list and to refresh it if any changes happen.
    private void orderListRefresh(ArrayList<Order> orderList, Customer currentUser){
        listModel.removeAllElements();
        for(Order o : orderList){
            if(o.getCustomerName().equals(currentUser.getUsername())){
                listModel.addElement(o.getDescription());
            }
        }
    }
}
