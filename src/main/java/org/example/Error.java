package org.example;

import javax.swing.*;

public class Error {
    public JFrame errorFrame = new JFrame();
    public Error(String typeOfError, String errorMessage){//För att använda detta kalla på konstruktören och skicka med 2 strängar
                                                        //den första är titeln på felet och den andra är text meddelandet.



        JLabel label = new JLabel(errorMessage);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        errorFrame.setContentPane(label);
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setTitle(typeOfError);
        errorFrame.setSize(400, 200);
        errorFrame.setLocationRelativeTo(null); // Shortcut for "place in middle of screen".
        errorFrame.setVisible(true);
        ImageIcon image = new ImageIcon("error.png");
        errorFrame.setIconImage(image.getImage());
    }
}
