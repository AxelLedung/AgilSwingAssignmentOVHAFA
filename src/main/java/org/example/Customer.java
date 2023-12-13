package org.example;

public class Customer extends User {
    private int balance;

    public Customer( String userName, String password, int balance) {
        super(userName, password);
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
