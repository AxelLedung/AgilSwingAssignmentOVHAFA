package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CardWindow {
    private JPanel cardWindow;
    private JButton addCardButton;
    private JButton cancelPurchaseButton;
    private JButton proceedWithPurchaseButton;
    private JPanel cardCheck;
    private JPanel cardCreate;
    private JPanel finalizePurchase;
    private JTextField cardNumberField;
    private JTextField ccvField;
    private JTextField pinField;
    private JButton addCardButton1;
    private JButton cancelPurchaseButton1;
    private JList orderJList;
    private JButton makePurchaseButton;
    private JButton goBackToCheckoutButton;
    private JLabel finalCost;
    private JLabel checkLabel;
    private JPanel pinCheck;
    private JTextField pinCheckField;
    private JButton makePurchaseButton1;
    private JButton cancelPurchaseButton2;
    private JLabel pinReturn;
    private JPanel purchaseComplete;
    private JButton goBackToShopButton;
    JFrame jFrame;
    DefaultListModel listModel = new DefaultListModel<>();
    private int cost;
    ArrayList<String> transferList = new ArrayList<>();
    private String name;
    private Card currentCard;
    private boolean pinChecked;
    int tries = 0;

    public CardWindow(Customer currentUser , ProductManager productManager, ArrayList<Product> productsToCheckoutArrayList, Admin admin){
        orderJList.setModel(listModel);
        jFrame = new JFrame("Checkout");
        jFrame.setContentPane(cardWindow);
        cardWindow.repaint();
        cardWindow.revalidate();
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkoutListRefresh(productsToCheckoutArrayList);
        boolean found = false;
        for(Card c : admin.CardList){
            if(c.getCardHolder().equals(currentUser.getUsername())){
                found = true;
                currentCard = new Card(c.getCardHolder(), c.getCardNumber(), c.getThreeDigits(), c.getPin());
                checkLabel.setText("A card has been registered under your name, you may proceed with purchase.");
                proceedWithPurchaseButton.setEnabled(true);
                addCardButton.setEnabled(false);
            }
        }
        if(!found){
            checkLabel.setText("No card found under your name, please add one in order to finalize purchase.");
            proceedWithPurchaseButton.setEnabled(false);
            addCardButton.setEnabled(true);
        }
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardWindow.removeAll();
                cardWindow.add(cardCreate);
                cardWindow.repaint();
                cardWindow.revalidate();
            }
        });
        proceedWithPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardWindow.removeAll();
                cardWindow.add(finalizePurchase);
                cardWindow.repaint();
                cardWindow.revalidate();
            }
        });
        cancelPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });

        addCardButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardHolder = currentUser.getUsername();
                int cardNumber = Integer.parseInt(cardNumberField.getText());
                int threeDigits = Integer.parseInt(ccvField.getText());
                int pinNumber = Integer.parseInt(pinField.getText());
                admin.CardList.add(new Card(cardHolder,cardNumber,threeDigits,pinNumber));
                cardWindow.removeAll();
                cardWindow.add(finalizePurchase);
                cardWindow.repaint();
                cardWindow.revalidate();
            }
        });
        cancelPurchaseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        makePurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean purchased = false;
                if (!pinChecked) {
                    cardWindow.removeAll();
                    cardWindow.add(pinCheck);
                    cardWindow.repaint();
                    cardWindow.revalidate();
                } else {
                    for (Product pr : productsToCheckoutArrayList) {
                        if (currentUser.getBalance() >= cost && pr.checkQuantity(pr.getQuantity())) {
                            purchased = true;
                        }
                    }
                    if (purchased) {
                        name = currentUser.getUsername();
                        for (Product p : productsToCheckoutArrayList) {
                            transferList.add(p.getName());
                        }
                        productManager.orderArrayList.add(new Order(name, cost, transferList));
                        currentUser.setBalance(currentUser.getBalance() - cost);
                        cardWindow.removeAll();
                        cardWindow.add(purchaseComplete);
                        cardWindow.repaint();
                        cardWindow.revalidate();
                    } else {
                        finalCost.setText("You don't have enough money to finalize the purchase" +
                                " or there aren't enough items in stock.");
                    }
                }
            }
        });
        goBackToCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        makePurchaseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                for(Card c : admin.CardList) {
                    if (Integer.parseInt(pinCheckField.getText()) == c.getPin() && currentUser.getUsername().equals(c.getCardHolder())) {
                        pinChecked = true;
                        found = true;
                        cardWindow.removeAll();
                        cardWindow.add(finalizePurchase);
                        cardWindow.repaint();
                        cardWindow.revalidate();
                    }
                }
                if(!found) {
                    pinReturn.setText("Incorrect PIN number, try again");
                    tries++;
                }
                if (tries == 3) {
                    pinReturn.setText("You have tried too many times.");
                    makePurchaseButton1.setEnabled(false);
                }
            }
        });
        cancelPurchaseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        goBackToShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPanel customerPanel = new CustomerPanel(productManager, currentUser, admin);
                jFrame.dispose();
            }
        });
    }

    private void checkoutListRefresh(ArrayList<Product> productsToCheckoutArrayList){
        cost = 0;
        for(Product p : productsToCheckoutArrayList){
            cost += p.getCost();
        }
        finalCost.setText("Final cost is: " + cost + " buckaroos.");
        listModel.removeAllElements();
        for(Product p : productsToCheckoutArrayList){
            listModel.addElement(p.getName() + "  Cost: " +  p.getCost());
        }
    }

}
