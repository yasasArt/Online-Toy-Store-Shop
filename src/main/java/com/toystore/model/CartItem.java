package com.toystore.model;

public class CartItem {
    private String cartId;
    private String customerUsername;
    private String toyId;
    private String toyName;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(String cartId, String customerUsername, String toyId,
                    String toyName, double price, int quantity) {
        this.cartId = cartId;
        this.customerUsername = customerUsername;
        this.toyId = toyId;
        this.toyName = toyName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    public String toFileString() {
        return cartId + "," + customerUsername + "," + toyId + "," +
                toyName + "," + price + "," + quantity;
    }

    public static CartItem fromFileString(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 6) {
            return null;
        }

        return new CartItem(
                data[0],
                data[1],
                data[2],
                data[3],
                Double.parseDouble(data[4]),
                Integer.parseInt(data[5])
        );
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
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
}