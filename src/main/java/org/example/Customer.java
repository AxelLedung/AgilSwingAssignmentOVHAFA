package org.example;

import java.util.ArrayList;
public class Customer extends User{
    private int id;
    private String username;
    private String password;
    private int balance;

    public Customer(int id, String username, String password, int balance) {
        super(id, username, password);
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
