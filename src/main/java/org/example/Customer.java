package org.example;

public class Customer extends User {
    private  static int nextCustomerId = 1;
    private int balance;

    public Customer( String userName, String password, int balance) {
        super(nextCustomerId, userName, password);
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
