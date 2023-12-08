package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Shop {
    private static File productsSaveFile = new File("products-save.txt");
    private static File usersSaveFile = new File("users-save.txt");
    private static File reviewsSaveFile = new File("reviews-save.txt");
    private static File ordersSaveFile = new File("orders-save.txt");
    ProductManager productManager = new ProductManager();
    Admin admin = new Admin();
    public Shop() {
        LoginPanel loginPanel = new LoginPanel(productManager, admin);
        Save(productManager, admin);
    }
    public static boolean Save(ProductManager productManager, Admin admin) {
        Load(productManager, admin);
        SaveProducts(productManager);
        return true;
    }
    public static boolean SaveProducts(ProductManager productManager) {
        try {
            FileWriter fileWriter = new FileWriter(productsSaveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < productManager.productArrayList.size(); i++) {
                bufferedWriter.write(productManager.productArrayList.get(i).GetCSV());
                if (i < productManager.productArrayList.size() - 1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            System.out.println("Saved succesfully!");
            //load function
            return true;
        }
        catch (Exception e) {
            System.out.println("Save failed...");
            //load funtion
            return false;
        }
    }
    public static boolean SaveUsers(Admin admin) {
        try {
            FileWriter fileWriter = new FileWriter(usersSaveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < admin.EmployeeList.size(); i++) {
                bufferedWriter.write(admin.EmployeeList.get(i).GetCSV());
                if (i < admin.EmployeeList.size() - 1) {
                    bufferedWriter.newLine();
                }
                else {
                    bufferedWriter.newLine();
                    bufferedWriter.write("@");
                    bufferedWriter.newLine();
                }
            }
            for (int i = 0; i < admin.CustomerList.size(); i++) {
                bufferedWriter.write(admin.CustomerList.get(i).GetCSV());
                if (i < admin.CustomerList.size() - 1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            System.out.println("Saved succesfully!");
            //load function
            return true;
        }
        catch (Exception e) {
            System.out.println("Save failed...");
            //load funtion
            return false;
        }
    }
    public static boolean SaveReviews() {
        return true;
    }
    public static boolean SaveOrders() {
        return true;
    }
    public static boolean Load(ProductManager productManager, Admin admin) {
        LoadProducts(productManager);
        return true;
    }
    private static boolean LoadProducts(ProductManager productManager) {
        try {
            FileReader fileReader = new FileReader(productsSaveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            productManager.productArrayList.clear();
            while (line != null) {
                String[] variables = line.split(",");
                String productName = variables[0];
                String productCategory = variables[1];
                int productCost = Integer.parseInt(variables[2]);
                int quantity = Integer.parseInt(variables[3]);
                productManager.productArrayList.add(new Product(productName, productCategory, productCost, quantity));
                line = bufferedReader.readLine();
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    private static boolean LoadUsers(Admin admin) {
        try {
            FileReader fileReader = new FileReader(usersSaveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            admin.EmployeeList.clear();
            admin.CustomerList.clear();
            while (line != null) {
                boolean customers = false;
                if (line == "@") {
                    customers = true;
                    line = bufferedReader.readLine();
                }
                if(!customers) {
                    String[] variables = line.split(",");
                    int id = Integer.parseInt(variables[0]);
                    String userName = variables[1];
                    String password = variables[2];
                    admin.EmployeeList.add(new Employee(userName, password));
                    line = bufferedReader.readLine();
                }
                else {
                    String[] variables = line.split(",");
                    String userName = variables[0];
                    String password = variables[1];
                    int balance = Integer.parseInt(variables[2]);
                    admin.CustomerList.add(new Customer(userName, password, balance));
                    line = bufferedReader.readLine();
                }
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
