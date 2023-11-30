package org.example;

public class Shop {
    public Shop() {
        ProductManager productManager = new ProductManager();
        Admin admin = new Admin();
        LoginPanel loginPanel = new LoginPanel(productManager, admin);
    }
}
