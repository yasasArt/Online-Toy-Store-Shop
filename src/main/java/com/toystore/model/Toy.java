package com.toystore.model;

public class Toy {
    private String toyId;
    private String toyName;
    private String category;
    private String ageGroup;
    private String brand;
    private double price;
    private int quantity;
    private String description;
    private String imageUrl;

    public Toy() {
    }

    public Toy(String toyId, String toyName, String category, String ageGroup,
               String brand, double price, int quantity, String description, String imageUrl) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.category = category;
        this.ageGroup = ageGroup;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String toFileString() {
        return toyId + "," + toyName + "," + category + "," + ageGroup + "," +
                brand + "," + price + "," + quantity + "," + description + "," + imageUrl;
    }

    public static Toy fromFileString(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 9) {
            return null;
        }

        return new Toy(
                data[0],
                data[1],
                data[2],
                data[3],
                data[4],
                Double.parseDouble(data[5]),
                Integer.parseInt(data[6]),
                data[7],
                data[8]
        );
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public double calculateTotalPrice(int orderQuantity) {
        return price * orderQuantity;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}