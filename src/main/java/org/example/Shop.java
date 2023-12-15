package org.example;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Shop {
    private static File productsSaveFile = new File("products-save.txt");
    private static File usersSaveFile = new File("users-save.txt");
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
        SaveCategories(productManager);
        SaveOrders(productManager);
        SaveProducts(productManager, admin);
        return true;
    }
    public static boolean Load(ProductManager productManager, Admin admin) {
        LoadUsers(admin);
        LoadCategories(productManager);
        LoadOrders(productManager);
        LoadProducts(productManager, admin);
        return true;
    }
    public static boolean SaveProducts(ProductManager productManager, Admin admin) {
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
            System.out.println("Saved Products and Reviews!");
            LoadProducts(productManager, admin);
            return true;
        }
        catch (Exception e) {
            System.out.println("Failed to save Products and Reviews!" + e);
            LoadProducts(productManager, admin);
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
            System.out.println("Saved Categories!");
            LoadCategories(productManager);
            return true;
        }
        catch (Exception e) {
            System.out.println("Failed to save Categories" + e);
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
            System.out.println("Saved Orders!");
            LoadOrders(productManager);
            return true;
        }
        catch (Exception e) {
            System.out.println("Failed to save Orders!" + e);
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
            System.out.println("Saved Users!");
            LoadUsers(admin);
            return true;
        }
        catch (Exception e) {
            System.out.println("Failed to save Users!" + e);
            LoadUsers(admin);
            return false;
        }
    }
    private static boolean LoadProducts(ProductManager productManager, Admin admin) {
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
                String[] reviewList = null;
                if (variables.length > 4) {
                    reviewList = variables[4].split("#");
                }

                // Checking if the chosen category exists if not create it. -- START
                String category = null;
                String categoryFound = null;
                for (String s : productManager.categoryArrayList) {
                    if (s.equals(productCategory) && categoryFound == null) {
                        categoryFound = s;
                    }
                }
                if (categoryFound == null) {
                    productManager.categoryArrayList.add(productCategory);
                    category = productCategory;
                }
                else {
                    category = categoryFound;
                }
                // Checking if the chosen category exists if not create it. -- END

                Product product = new Product(productName, category, productCost, quantity);
                productManager.productArrayList.add(product);
                //Turn review string into a Review Object -- START
                if (reviewList != null)  {
                    ArrayList<Review> reviewArrayList = new ArrayList<Review>();
                    for (int i = 0; i < reviewList.length; i++) {
                        String[] review = reviewList[i].split("@");
                        String reviewText = review[0];
                        String reviewCustomerString = review[1];
                        int reviewRating = Integer.parseInt(review[2]);
                        Customer reviewCustomer = null;
                        try {
                            for (Customer c : admin.CustomerList) {
                                if (c.getUsername().equals(reviewCustomerString)) {
                                    reviewCustomer = c;
                                }
                            }
                            if (reviewCustomer != null) {
                                reviewArrayList.add(new Review(reviewText, reviewCustomer, reviewRating));
                            }
                        }
                        catch (Exception e) {
                            System.out.println("Review was not able to load to removal of user");
                        }
                    }
                    product.setReviewArrayList(reviewArrayList);
                }
                else {
                }
                //Turn review string into a Review Object -- END

                line = bufferedReader.readLine();
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("Products failed to load: " + e);
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
                        admin.AddEmloyee(userName, password);
                        line = bufferedReader.readLine();
                    }
                    else {
                        String[] variables = line.split(",");
                        String userName = variables[0];
                        String password = variables[1];
                        int balance = Integer.parseInt(variables[2]);
                        admin.AddCustomer(userName, password, balance);
                        line = bufferedReader.readLine();
                    }
                }
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("Users failed to load: " + e);
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
            System.out.println("Categories failed to load: " + e);
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
                int id = Integer.parseInt(variables[0]);
                String customerName = variables[1];
                int orderSum = Integer.parseInt(variables[2]);
                String[] orderListStringArray = variables[3].split("#");
                ArrayList<String> orderList = new ArrayList<String>();
                orderList.addAll(Arrays.asList(orderListStringArray));
                productManager.orderArrayList.add(new Order(id, customerName, orderSum, orderList));
                line = bufferedReader.readLine();
            }
            return true;
        }
        catch (Exception e) {
            System.out.println("Orders failed to load: " + e);
            return false;
        }
    }
}
