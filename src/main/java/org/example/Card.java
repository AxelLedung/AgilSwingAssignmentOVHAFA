package org.example;

public class Card {
    String cardHolder;
    int cardNumber;
    int threeDigits;
    int pin;

    public Card(String cardHolder, int cardNumber, int threeDigits, int pin) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.threeDigits = threeDigits;
        this.pin = pin;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getThreeDigits() {
        return threeDigits;
    }

    public void setThreeDigits(int threeDigits) {
        this.threeDigits = threeDigits;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
