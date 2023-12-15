package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.CertificateRevokedException;
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
    private boolean pinChecked;
    private int tries = 0;

    // Creates the cardCheck window.
    public CardWindow(Customer currentUser , ProductManager productManager,
                      ArrayList<Product> productsToCheckoutArrayList, Admin admin){
        orderJList.setModel(listModel);
        jFrame = new JFrame("Checkout");
        jFrame.setContentPane(cardWindow);
        cardWindow.repaint();
        cardWindow.revalidate();
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        checkoutListRefresh(productsToCheckoutArrayList);
        boolean found = false;
        // Goes through the list of already registered cards to see if the currently active user has one,
        // if they do the "add card" button will be locked and if they do not the "make purchase" button will instead
        // be locked.
        for(Card c : admin.CardList){
            if(c.getCardHolder().equals(currentUser.getUsername())){
                found = true;
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
        // Sets "cardCreate" as the active panel. There you can register a card into the system.
        addCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardWindow.removeAll();
                cardWindow.add(cardCreate);
                cardWindow.repaint();
                cardWindow.revalidate();
            }
        });
        // Sets "finalizePurchase" as the active panel. There you can complete your purchase.
        proceedWithPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardWindow.removeAll();
                cardWindow.add(finalizePurchase);
                cardWindow.repaint();
                cardWindow.revalidate();
            }
        });
        // Takes you back to the CheckoutWindow if pressed.
        cancelPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager,
                        productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        // Will register a new card into the system using the provided variables and then set
        // "finalizePurchase" as the active panel.
        addCardButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cardNumberField.getText().isEmpty() ||
                        !ccvField.getText().isEmpty() || !pinField.getText().isEmpty()) {
                    try{
                        String cardHolder = currentUser.getUsername();
                        int cardNumber = Integer.parseInt(cardNumberField.getText());
                        int threeDigits = Integer.parseInt(ccvField.getText());
                        int pinNumber = Integer.parseInt(pinField.getText());
                        admin.CardList.add(new Card(cardHolder, cardNumber, threeDigits, pinNumber));
                        cardWindow.removeAll();
                        cardWindow.add(finalizePurchase);
                        cardWindow.repaint();
                        cardWindow.revalidate();
                    }catch (Exception ex){
                        Error error = new Error("Invalid inputs!", "Please input only numbers!");
                    }
                } else if (ccvField.getText().length() > 3 || pinField.getText().length() > 4) {
                    Error error = new Error("CCV/CV2 or PIN entered is too long."
                            , "CCV/CV2 can only be up to 3 numbers and PIN can only be up to 4.");
                } else{
                    Error error = new Error("Empty fields!", "Don't leave any fields empty.");
                }
            }
        });
        // Takes you back to the CheckoutWindow if pressed.
        cancelPurchaseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager,
                        productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        // When pressed if the active user hasn't verified their PIN-number they will be taken to the "pinCheck" panel
        // where the user can verify their PIN. If they are verified then the code will go through the items in the
        // cart, total up the cost, check if there are enough items in stock and then if both are true the purchase
        // will be made and the current user, the total cost and all items will be added to a list of Order objects.
        // You will then lastly be thrown to the "purchaseComplete" window that tells you that the purchase was
        // successful.
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
        // Takes you back to the CheckoutWindow if pressed.
        goBackToCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager,
                        productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        // Goes through the list of cards to check if the inputted PIN is the same as the PIN listed under the
        // current user's username, if this is true then the user will be thrown back into the "finalizePurchase"
        // window where they will be allowed to complete their purchase. If it wasn't true then the user will have
        // two more chances to input the correct PIN-number otherwise the "make purchase button" will be locked.
        makePurchaseButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pinCheckField.getText().isEmpty()) {
                    try {
                        boolean found = false;
                        for (Card c : admin.CardList) {
                            if (Integer.parseInt(pinCheckField.getText()) == c.getPin()
                                    && currentUser.getUsername().equals(c.getCardHolder())) {
                                pinChecked = true;
                                found = true;
                                cardWindow.removeAll();
                                cardWindow.add(finalizePurchase);
                                cardWindow.repaint();
                                cardWindow.revalidate();
                            }
                        }
                        if (!found) {
                            pinReturn.setText("Incorrect PIN number, try again");
                            tries++;
                        }
                        if (tries == 3) {
                            pinReturn.setText("You have tried too many times.");
                            makePurchaseButton1.setEnabled(false);
                        }
                    }catch (Exception ex){
                        Error error = new Error("Invalid input!","Please use only numbers.");
                    }
                } else if (pinCheckField.getText().length() > 4) {
                    Error error = new Error("PIN-number is too long!","PIN number cannot be longer than 4 digits.");
                }else {
                    Error error = new Error("Empty field!","Don't leave the field empty.");
                }
            }
        });
        // Takes you back to the CheckoutWindow if pressed.
        cancelPurchaseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckoutWindow checkoutWindow = new CheckoutWindow(currentUser, productManager, productsToCheckoutArrayList, admin);
                jFrame.dispose();
            }
        });
        // Takes you back to the Storefront if pressed.
        goBackToShopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPanel customerPanel = new CustomerPanel(productManager, currentUser, admin);
                jFrame.dispose();
            }
        });
    }
    // Used to create the visual list used by "finalizePurchase" to display what items the user has added to
    // their cart.
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
