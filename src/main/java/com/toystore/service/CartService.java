package com.toystore.service;

import com.toystore.model.CartItem;
import com.toystore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartService {

    public boolean addToCart(CartItem item) {
        CartItem existingItem = getCartItem(item.getCustomerUsername(), item.getToyId());

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            return updateCartItem(existingItem);
        }

        String sql = "INSERT INTO cart (cart_id, customer_username, toy_id, toy_name, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getCartId());
            ps.setString(2, item.getCustomerUsername());
            ps.setString(3, item.getToyId());
            ps.setString(4, item.getToyName());
            ps.setDouble(5, item.getPrice());
            ps.setInt(6, item.getQuantity());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<CartItem> getAllCartItems() {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT * FROM cart";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(mapCartItem(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<CartItem> getCartByCustomer(String username) {
        List<CartItem> items = new ArrayList<>();
        String sql = "SELECT * FROM cart WHERE customer_username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                items.add(mapCartItem(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public CartItem getCartItem(String username, String toyId) {
        String sql = "SELECT * FROM cart WHERE customer_username = ? AND toy_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, toyId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapCartItem(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateCartItem(CartItem item) {
        String sql = "UPDATE cart SET quantity = ? WHERE cart_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getQuantity());
            ps.setString(2, item.getCartId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean removeCartItem(String cartId) {
        String sql = "DELETE FROM cart WHERE cart_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cartId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean clearCustomerCart(String username) {
        String sql = "DELETE FROM cart WHERE customer_username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            return ps.executeUpdate() >= 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public double getCartTotal(String username) {
        double total = 0;
        String sql = "SELECT SUM(price * quantity) AS total FROM cart WHERE customer_username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public String generateCartId() {
        String sql = "SELECT COUNT(*) FROM cart";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return "CART" + String.format("%03d", rs.getInt(1) + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "CART001";
    }

    private CartItem mapCartItem(ResultSet rs) throws SQLException {
        return new CartItem(
                rs.getString("cart_id"),
                rs.getString("customer_username"),
                rs.getString("toy_id"),
                rs.getString("toy_name"),
                rs.getDouble("price"),
                rs.getInt("quantity")
        );
    }
}