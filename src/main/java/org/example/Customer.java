package org.example;

public class Customer extends User {
    String userName;
    String password;
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
    @Override
    public String getCSV() {
        return userName + "," + password + "," + balance;
    }
}
