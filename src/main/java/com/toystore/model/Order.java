package com.toystore.model;

public class Order {
    private String orderId;
    private String customerUsername;
    private String toyId;
    private String toyName;
    private int quantity;
    private double totalAmount;
    private String orderDate;
    private String deliveryAddress;
    private String status;

    public Order() {
    }

    public Order(String orderId, String customerUsername, String toyId, String toyName,
                 int quantity, double totalAmount, String orderDate,
                 String deliveryAddress, String status) {
        this.orderId = orderId;
        this.customerUsername = customerUsername;
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }

    public String toFileString() {
        return orderId + "," + customerUsername + "," + toyId + "," + toyName + "," +
                quantity + "," + totalAmount + "," + orderDate + "," +
                deliveryAddress + "," + status;
    }

    public static Order fromFileString(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 9) {
            return null;
        }

        return new Order(
                data[0],
                data[1],
                data[2],
                data[3],
                Integer.parseInt(data[4]),
                Double.parseDouble(data[5]),
                data[6],
                data[7],
                data[8]
        );
    }

    public boolean isPending() {
        return "Pending".equalsIgnoreCase(status);
    }

    public boolean isDelivered() {
        return "Delivered".equalsIgnoreCase(status);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}