package com.toystore.model;

public class Toy {
    private String toyId;
    private String toyName;
    private String category;
    private String ageGroup;
    private double price;
    private int quantity;

    public Toy() {
    }

    public Toy(String toyId, String toyName, String category, String ageGroup, double price, int quantity) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.category = category;
        this.ageGroup = ageGroup;
        this.price = price;
        this.quantity = quantity;
    }

    public String getToyId() {
        return toyId;
    }

    public void setToyId(String toyId) {
        this.toyId = toyId;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toFileString() {
        return toyId + "," + toyName + "," + category + "," + ageGroup + "," + price + "," + quantity;
    }

    public String displayDetails() {
        return "Toy: " + toyName + " | Category: " + category;
    }
}