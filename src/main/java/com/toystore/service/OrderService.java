package com.toystore.service;

import com.toystore.model.Order;
import com.toystore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, customer_name, toy_id, quantity, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getCustomerName());
            ps.setString(3, order.getToyId());
            ps.setInt(4, order.getQuantity());
            ps.setString(5, order.getStatus());

            int rowsInserted = ps.executeUpdate();
            System.out.println("Inserted rows: " + rowsInserted);

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Order order = new Order(
                        rs.getString("order_id"),
                        rs.getString("customer_name"),
                        rs.getString("toy_id"),
                        rs.getInt("quantity"),
                        rs.getString("status")
                );
                orderList.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    public Order searchOrderById(String orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getString("order_id"),
                        rs.getString("customer_name"),
                        rs.getString("toy_id"),
                        rs.getInt("quantity"),
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateOrder(Order order) {
        String sql = "UPDATE orders SET customer_name=?, toy_id=?, quantity=?, status=? WHERE order_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getToyId());
            ps.setInt(3, order.getQuantity());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getOrderId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteOrder(String orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, orderId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}