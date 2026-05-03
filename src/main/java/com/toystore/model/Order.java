package com.toystore.model;

public class Order {
    private String orderId;
    private String customerName;
    private String toyId;
    private int quantity;
    private String status;

    public Order() {
    }

    public Order(String orderId, String customerName, String toyId, int quantity, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.toyId = toyId;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getToyId() {
        return toyId;
    }

    public void setToyId(String toyId) {
        this.toyId = toyId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return orderId + "," + customerName + "," + toyId + "," + quantity + "," + status;
    }
}