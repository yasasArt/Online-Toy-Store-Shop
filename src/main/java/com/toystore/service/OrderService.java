package com.toystore.service;

import com.toystore.model.Order;
import com.toystore.util.FileUtil;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private String getFilePath() {
        return FileUtil.getFilePath("orders.txt");
    }

    public boolean addOrder(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(), true))) {
            writer.write(order.toFileString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Order order = Order.fromFileString(line);
                    if (order != null) orders.add(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) return order;
        }
        return null;
    }

    public List<Order> getOrdersByCustomer(String username) {
        List<Order> result = new ArrayList<>();
        for (Order order : getAllOrders()) {
            if (order.getCustomerUsername().equalsIgnoreCase(username)) result.add(order);
        }
        return result;
    }

    public boolean updateOrder(Order updatedOrder) {
        List<Order> orders = getAllOrders();
        boolean updated = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
            for (Order order : orders) {
                if (order.getOrderId().equalsIgnoreCase(updatedOrder.getOrderId())) {
                    writer.write(updatedOrder.toFileString());
                    updated = true;
                } else {
                    writer.write(order.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updated;
    }

    public boolean updateOrderStatus(String orderId, String status) {
        Order order = getOrderById(orderId);
        if (order == null) return false;
        order.setStatus(status);
        return updateOrder(order);
    }

    public boolean deleteOrder(String orderId) {
        List<Order> orders = getAllOrders();
        boolean deleted = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath()))) {
            for (Order order : orders) {
                if (!order.getOrderId().equalsIgnoreCase(orderId)) {
                    writer.write(order.toFileString());
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

    public int getTotalOrders() { return getAllOrders().size(); }

    public double getTotalSales() {
        double total = 0;
        for (Order o : getAllOrders()) {
            if (!"Cancelled".equalsIgnoreCase(o.getStatus())) total += o.getTotalAmount();
        }
        return total;
    }

    public String generateOrderId() {
        return "ORD" + String.format("%03d", getAllOrders().size() + 1);
    }
}
