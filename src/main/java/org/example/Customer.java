package org.example;

public class Customer extends User {
    private  static int nextCustomerId = 1;
    private int balance;

    public Customer( String username, String password, int balance) {
        super(nextCustomerId, username, password);
        nextCustomerId++;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
