package org.example;

public class Customer extends User {
    private int balance;

    public Customer(int id, String username, String password, int balance) {
        super(id, username, password);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
