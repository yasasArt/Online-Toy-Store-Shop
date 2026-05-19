package com.toystore.service;

import com.toystore.model.Order;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final String FILE_PATH = "data/orders.txt";

    public OrderService() {
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addOrder(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
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

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Order order = Order.fromFileString(line);
                    if (order != null) {
                        orders.add(order);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrdersByCustomer(String username) {
        List<Order> customerOrders = new ArrayList<>();

        for (Order order : getAllOrders()) {
            if (order.getCustomerUsername().equalsIgnoreCase(username)) {
                customerOrders.add(order);
            }
        }

        return customerOrders;
    }

    public boolean updateOrder(Order updatedOrder) {
        List<Order> orders = getAllOrders();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
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

        if (order == null) {
            return false;
        }

        order.setStatus(status);
        return updateOrder(order);
    }

    public boolean deleteOrder(String orderId) {
        List<Order> orders = getAllOrders();
        boolean deleted = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
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

    public int getTotalOrders() {
        return getAllOrders().size();
    }

    public double getTotalSales() {
        double total = 0;

        for (Order order : getAllOrders()) {
            if (!"Cancelled".equalsIgnoreCase(order.getStatus())) {
                total += order.getTotalAmount();
            }
        }

        return total;
    }

    public String generateOrderId() {
        int count = getAllOrders().size() + 1;
        return "ORD" + String.format("%03d", count);
    }
}
