package org.example;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Shop {
    private static File productsSaveFile = new File("products-save.txt");
    private static File usersSaveFile = new File("users-save.txt");
    private static File reviewsSaveFile = new File("reviews-save.txt");
    private static File categorySaveFile = new File("category-save.txt");
    private static File ordersSaveFile = new File("orders-save.txt");
    ProductManager productManager = new ProductManager();
    Admin admin = new Admin();
    public Shop() {
        Load(productManager, admin);
        LoginPanel loginPanel = new LoginPanel(productManager, admin);
    }
    public static boolean Save(ProductManager productManager, Admin admin) {
        SaveUsers(admin);
        SaveReviews(productManager);
        SaveCategories(productManager);
        SaveOrders(productManager);
        SaveProducts(productManager);
        return true;
    }
    public static boolean Load(ProductManager productManager, Admin admin) {
        LoadUsers(admin);
        LoadCategories(productManager);
        //LoadReviews
        LoadOrders(productManager);
        LoadProducts(productManager);
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
            LoadProducts(productManager);
            return true;
        }
        catch (Exception e) {
            System.out.println("Save failed...");
            LoadProducts(productManager);
            return false;
        }
    }
    public static boolean SaveCategories(ProductManager productManager) {
        try {
            FileWriter fileWriter = new FileWriter(categorySaveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < productManager.categoryArrayList.size(); i++) {
                bufferedWriter.write(productManager.categoryArrayList.get(i));
                if (i < productManager.categoryArrayList.size() - 1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            System.out.println("Saved succesfully!");
            LoadCategories(productManager);
            return true;
        }
        catch (Exception e) {
            System.out.println("Save failed...");
            LoadCategories(productManager);
            return false;
        }
    }
    public static boolean SaveOrders(ProductManager productManager) {
        try {
            FileWriter fileWriter = new FileWriter(ordersSaveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < productManager.orderArrayList.size(); i++) {
                bufferedWriter.write(productManager.orderArrayList.get(i).getCSV());
                if (i < productManager.orderArrayList.size() - 1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            System.out.println("Saved succesfully!");
            LoadOrders(productManager);
            return true;
        }
        catch (Exception e) {
            System.out.println("Save failed...");
            LoadOrders(productManager);
            return false;
        }
    }
    public static boolean SaveUsers(Admin admin) {
        try {
            FileWriter fileWriter = new FileWriter(usersSaveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < admin.EmployeeList.size(); i++) {
                bufferedWriter.write(admin.EmployeeList.get(i).getCSV());
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
                bufferedWriter.write(admin.CustomerList.get(i).getCSV());
                if (i < admin.CustomerList.size() - 1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            System.out.println("Saved succesfully!");
            LoadUsers(admin);
            return true;
        }
        catch (Exception e) {
            System.out.println("Save failed...");
            LoadUsers(admin);
            return false;
        }
    }
    public static boolean SaveReviews(ProductManager productManager) {
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
                String category = null;
                String categoryFound = null;
                for (String s : productManager.categoryArrayList) {
                    if (s.equals(productCategory) && categoryFound == null) {
                        categoryFound = s;
                    }
                }
                if (categoryFound == null) {
                    productManager.categoryArrayList.add(productCategory);
                    productManager.productArrayList.add(new Product(productName, productCategory, productCost, quantity));
                }
                else {
                    category = categoryFound;
                    productManager.productArrayList.add(new Product(productName, category, productCost, quantity));
                }
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
            boolean customers = false;
            while (line != null) {
                if (line.equals("@")) {
                    customers = true;
                    line = bufferedReader.readLine();
                }
                else {
                    if(!customers) {
                        String[] variables = line.split(",");
                        String userName = variables[0];
                        String password = variables[1];
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
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    private static boolean LoadCategories(ProductManager productManager) {
        try {
            FileReader fileReader = new FileReader(categorySaveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            productManager.categoryArrayList.clear();
            while (line != null) {
                String[] variables = line.split(",");
                String categoryName = variables[0];
                productManager.categoryArrayList.add(categoryName);
                line = bufferedReader.readLine();
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    private static boolean LoadOrders(ProductManager productManager) {
        try {
            FileReader fileReader = new FileReader(ordersSaveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            productManager.orderArrayList.clear();
            while (line != null) {
                String[] variables = line.split(",");
                String customerName = variables[0];
                int orderSum = Integer.parseInt(variables[1]);
                String[] orderListStringArray = variables[2].split("#");
                ArrayList<String> orderList = new ArrayList<String>();
                orderList.addAll(Arrays.asList(orderListStringArray));
                productManager.orderArrayList.add(new Order(customerName, orderSum, orderList));
                line = bufferedReader.readLine();
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    private static boolean LoadReviews(ProductManager productManager) {
        try {
            FileReader fileReader = new FileReader(ordersSaveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            productManager.orderArrayList.clear();
            while (line != null) {
                DENNA FUNKTION ÄR INTE KLAR
                String[] variables = line.split(",");
                String customerName = variables[0];
                int orderSum = Integer.parseInt(variables[1]);
                String[] orderListStringArray = variables[2].split("#");
                ArrayList<String> orderList = new ArrayList<String>();
                orderList.addAll(Arrays.asList(orderListStringArray));
                productManager.orderArrayList.add(new Order(customerName, orderSum, orderList));
                line = bufferedReader.readLine();
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
