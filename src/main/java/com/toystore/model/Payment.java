package com.toystore.model;

public class Payment {
    private String paymentId;
    private String orderId;
    private String customerUsername;
    private String paymentMethod;
    private double amount;
    private String paymentStatus;
    private String paymentDate;

    public Payment() {
    }

    public Payment(String paymentId, String orderId, String customerUsername,
                   String paymentMethod, double amount, String paymentStatus,
                   String paymentDate) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerUsername = customerUsername;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    public String toFileString() {
        return paymentId + "," + orderId + "," + customerUsername + "," +
                paymentMethod + "," + amount + "," + paymentStatus + "," + paymentDate;
    }

    public static Payment fromFileString(String line) {
        String[] data = line.split(",", -1);
        if (data.length < 7) {
            return null;
        }

        return new Payment(
                data[0],
                data[1],
                data[2],
                data[3],
                Double.parseDouble(data[4]),
                data[5],
                data[6]
        );
    }

    public boolean isPaid() {
        return "Paid".equalsIgnoreCase(paymentStatus);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}