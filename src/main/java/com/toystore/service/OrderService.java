package com.toystore.service;

import com.toystore.model.Order;
import com.toystore.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final String FILE_NAME = "orders.txt";

    public boolean addOrder(Order order) {
        if (searchOrderById(order.getOrderId()) != null) {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileUtil.getFilePath(FILE_NAME), true))) {
            bw.write(order.toFileString());
            bw.newLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FileUtil.getFilePath(FILE_NAME)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 5) {
                    Order order = new Order(
                            d[0],
                            d[1],
                            d[2],
                            Integer.parseInt(d[3]),
                            d[4]
                    );
                    orderList.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public Order searchOrderById(String orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }
        return null;
    }

    public boolean updateOrder(Order updatedOrder) {
        List<Order> orders = getAllOrders();
        boolean found = false;

        for (Order order : orders) {
            if (order.getOrderId().equalsIgnoreCase(updatedOrder.getOrderId())) {
                order.setCustomerName(updatedOrder.getCustomerName());
                order.setToyId(updatedOrder.getToyId());
                order.setQuantity(updatedOrder.getQuantity());
                order.setStatus(updatedOrder.getStatus());
                found = true;
                break;
            }
        }

        return found && writeAllOrders(orders);
    }

    public boolean deleteOrder(String orderId) {
        List<Order> orders = getAllOrders();
        boolean removed = orders.removeIf(order -> order.getOrderId().equalsIgnoreCase(orderId));
        return removed && writeAllOrders(orders);
    }

    private boolean writeAllOrders(List<Order> orders) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileUtil.getFilePath(FILE_NAME), false))) {
            for (Order order : orders) {
                bw.write(order.toFileString());
                bw.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}