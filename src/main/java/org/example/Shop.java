package org.example;

public class Shop {
    public Shop() {
        ProductManager productManager = new ProductManager();
        CustomerPanel customerPanel = new CustomerPanel(productManager);
    }
}
