package com.toystore.service;

import com.toystore.model.Payment;
import com.toystore.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private String getFilePath() {
        return FileUtil.getFilePath("payments.txt");
    }

    public boolean addPayment(Payment payment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), true))) {
            writer.write(payment.toFileString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Payment payment = Payment.fromFileString(line);
                    if (payment != null) payments.add(payment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public Payment getPaymentById(String paymentId) {
        for (Payment payment : getAllPayments()) {
            if (payment.getPaymentId().equalsIgnoreCase(paymentId)) return payment;
        }
        return null;
    }

    public Payment getPaymentByOrderId(String orderId) {
        for (Payment payment : getAllPayments()) {
            if (payment.getOrderId().equalsIgnoreCase(orderId)) return payment;
        }
        return null;
    }

    public List<Payment> getPaymentsByCustomer(String username) {
        List<Payment> result = new ArrayList<>();
        for (Payment payment : getAllPayments()) {
            if (payment.getCustomerUsername().equalsIgnoreCase(username)) result.add(payment);
        }
        return result;
    }

    public boolean updatePayment(Payment updatedPayment) {
        List<Payment> payments = getAllPayments();
        boolean updated = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
            for (Payment payment : payments) {
                if (payment.getPaymentId().equalsIgnoreCase(updatedPayment.getPaymentId())) {
                    writer.write(updatedPayment.toFileString());
                    updated = true;
                } else {
                    writer.write(payment.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean updatePaymentStatus(String paymentId, String status) {
        Payment payment = getPaymentById(paymentId);
        if (payment == null) return false;
        payment.setPaymentStatus(status);
        return updatePayment(payment);
    }

    public boolean deletePayment(String paymentId) {
        List<Payment> payments = getAllPayments();
        boolean deleted = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
            for (Payment payment : payments) {
                if (!payment.getPaymentId().equalsIgnoreCase(paymentId)) {
                    writer.write(payment.toFileString());
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    public double getTotalPaidAmount() {
        double total = 0;
        for (Payment p : getAllPayments()) {
            if ("Paid".equalsIgnoreCase(p.getPaymentStatus())) total += p.getAmount();
        }
        return total;
    }

    public String generatePaymentId() {
        return "PAY" + String.format("%03d", getAllPayments().size() + 1);
    }
}
