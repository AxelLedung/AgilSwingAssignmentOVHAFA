package org.example;

import java.util.ArrayList;

public class CustomerManager {

    ArrayList<Customer> customerList = new ArrayList<>();

    public CustomerManager(){
        customerList.add(new Customer(1,"Bobgamer","bobgamer123", 3500));
        customerList.add(new Customer(2,"Vera1","xyz456", 560));
        customerList.add(new Customer(3,"YesnoYes","NoyesNo", 380000));
        customerList.add(new Customer(7,"Test","Test", 35));
        customerList.add(new Customer(5,"NotAdmin","NotAdmin1", 3601));
        customerList.add(new Customer(8,"No","Yes", 123));
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
}
