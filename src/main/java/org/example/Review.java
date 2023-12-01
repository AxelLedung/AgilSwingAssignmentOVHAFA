package org.example;

public class Review {
    private String text;
    private int rating;
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
    public Review(String text) {
        this.text = text;
    }
}
