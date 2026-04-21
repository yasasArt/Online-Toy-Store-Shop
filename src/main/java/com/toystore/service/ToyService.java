package com.toystore.service;

import com.toystore.model.Toy;
import com.toystore.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToyService {

    public boolean addToy(Toy toy) {
        String sql = "INSERT INTO toys (toy_id, toy_name, category, age_group, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Database connection failed.");
                return false;
            }

            System.out.println("Database connected successfully.");

            ps.setString(1, toy.getToyId());
            ps.setString(2, toy.getToyName());
            ps.setString(3, toy.getCategory());
            ps.setString(4, toy.getAgeGroup());
            ps.setDouble(5, toy.getPrice());
            ps.setInt(6, toy.getQuantity());

            int rowsInserted = ps.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("SQL error while inserting toy.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error while inserting toy.");
            e.printStackTrace();
        }

        return false;
    }

    public List<Toy> getAllToys() {
        List<Toy> toys = new ArrayList<>();
        String sql = "SELECT * FROM toys";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Toy toy = new Toy(
                        rs.getString("toy_id"),
                        rs.getString("toy_name"),
                        rs.getString("category"),
                        rs.getString("age_group"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                toys.add(toy);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return toys;
    }

    public Toy searchToyById(String toyId) {
        String sql = "SELECT * FROM toys WHERE toy_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, toyId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Toy(
                        rs.getString("toy_id"),
                        rs.getString("toy_name"),
                        rs.getString("category"),
                        rs.getString("age_group"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateToy(Toy toy) {
        String sql = "UPDATE toys SET toy_name=?, category=?, age_group=?, price=?, quantity=? WHERE toy_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, toy.getToyName());
            ps.setString(2, toy.getCategory());
            ps.setString(3, toy.getAgeGroup());
            ps.setDouble(4, toy.getPrice());
            ps.setInt(5, toy.getQuantity());
            ps.setString(6, toy.getToyId());

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
}