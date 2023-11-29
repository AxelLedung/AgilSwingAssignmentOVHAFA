package org.example;

import java.util.ArrayList;

public class Order {
    static int nextId;
    private int id;
    private String customerName;
    private int orderSum;
    private ArrayList<String> orderList;

    public Order(String customerName, int orderSum, ArrayList<String> orderList) {
        this.id = nextId;
        nextId++;
        this.customerName = customerName;
        this.orderSum = orderSum;
        this.orderList = orderList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(int orderSum) {
        this.orderSum = orderSum;
    }

    public ArrayList<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }
}
