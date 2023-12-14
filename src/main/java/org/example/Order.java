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

    public String getDescription(){
        String bracketLess = orderList.toString().replace("[","").replace("]","");
        return id + ", " + customerName + ", " + orderSum + ", " + bracketLess;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getId(){
        return id;
    }

    public int getSum(){
        return orderSum;
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

    public String getOrderItem(ArrayList<String> listItem) {
        return listItem.toString();
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }
    public String getCSV() {
        String orderListString = null;
        if (!orderList.isEmpty()) {
            for (int i = 0; i < orderList.size(); i++) {
                if (i == orderList.size() - 1)
                {
                    orderListString = orderListString += orderList.get(i);
                }
                else {
                    orderListString = orderListString += orderList.get(i) + "#";
                }
            }
        }
        return customerName + "," + orderSum + "," + orderListString;
    }
}
