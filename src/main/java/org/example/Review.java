package org.example;

public class Review {
    private String text;
    private int rating;
    private Customer customer;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Review(String text, Customer currentUser, int selectedRating) {
        this.text = text;
        this.customer = currentUser;
        this.rating = selectedRating;
        //la till rating i constructor
    }
}
