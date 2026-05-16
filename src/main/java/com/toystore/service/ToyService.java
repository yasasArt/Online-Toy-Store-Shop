package com.toystore.service;

import com.toystore.model.Toy;
import com.toystore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToyService {

    public boolean addToy(Toy toy) {
        String sql = "INSERT INTO toys (toy_id, toy_name, category, age_group, brand, price, quantity, description, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, toy.getToyId());
            ps.setString(2, toy.getToyName());
            ps.setString(3, toy.getCategory());
            ps.setString(4, toy.getAgeGroup());
            ps.setString(5, toy.getBrand());
            ps.setDouble(6, toy.getPrice());
            ps.setInt(7, toy.getQuantity());
            ps.setString(8, toy.getDescription());
            ps.setString(9, toy.getImageUrl());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Toy ID already exists.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Toy> getAllToys() {
        List<Toy> toys = new ArrayList<>();
        String sql = "SELECT * FROM toys";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                toys.add(mapToy(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return toys;
    }

    public Toy getToyById(String toyId) {
        String sql = "SELECT * FROM toys WHERE toy_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, toyId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapToy(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Toy> searchToys(String keyword) {
        List<Toy> toys = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllToys();
        }

        String sql = "SELECT * FROM toys WHERE toy_name LIKE ? OR category LIKE ? OR age_group LIKE ? OR brand LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String search = "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                toys.add(mapToy(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return toys;
    }

    public boolean updateToy(Toy toy) {
        String sql = "UPDATE toys SET toy_name=?, category=?, age_group=?, brand=?, price=?, quantity=?, description=?, image_url=? WHERE toy_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, toy.getToyName());
            ps.setString(2, toy.getCategory());
            ps.setString(3, toy.getAgeGroup());
            ps.setString(4, toy.getBrand());
            ps.setDouble(5, toy.getPrice());
            ps.setInt(6, toy.getQuantity());
            ps.setString(7, toy.getDescription());
            ps.setString(8, toy.getImageUrl());
            ps.setString(9, toy.getToyId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteToy(String toyId) {
        String sql = "DELETE FROM toys WHERE toy_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, toyId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean reduceStock(String toyId, int quantity) {
        Toy toy = getToyById(toyId);

        if (toy == null || toy.getQuantity() < quantity) {
            return false;
        }

        String sql = "UPDATE toys SET quantity = quantity - ? WHERE toy_id = ? AND quantity >= ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setString(2, toyId);
            ps.setInt(3, quantity);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public String generateToyId() {
        String sql = "SELECT COUNT(*) FROM toys";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return "T" + String.format("%03d", rs.getInt(1) + 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "T001";
    }

    private Toy mapToy(ResultSet rs) throws SQLException {
        return new Toy(
                rs.getString("toy_id"),
                rs.getString("toy_name"),
                rs.getString("category"),
                rs.getString("age_group"),
                rs.getString("brand"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getString("description"),
                rs.getString("image_url")
        );
    }
}