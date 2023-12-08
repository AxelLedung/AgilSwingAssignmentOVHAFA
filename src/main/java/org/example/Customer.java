package org.example;

public class Customer extends User {
    private static int nextCustomerId = 1;
    int id;
    String userName;
    String password;
    private int balance;

    public Customer( String userName, String password, int balance) {
        super(nextCustomerId, userName, password);
        this.id = nextCustomerId;
        this.userName = userName;
        this.password = password;
        nextCustomerId++;
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    @Override
    public String GetCSV() {
        return userName + "," + password + "," + balance;
    }
}
