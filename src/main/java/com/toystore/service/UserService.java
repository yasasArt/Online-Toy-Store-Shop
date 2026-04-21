package com.toystore.service;

import com.toystore.model.User;
import com.toystore.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public boolean registerUser(User user) {
        String checkSql = "SELECT * FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users (full_name, email, username, password, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkPs = conn.prepareStatement(checkSql)) {

            checkPs.setString(1, user.getUsername());
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                return false;
            }

            try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
                insertPs.setString(1, user.getFullName());
                insertPs.setString(2, user.getEmail());
                insertPs.setString(3, user.getUsername());
                insertPs.setString(4, user.getPassword());
                insertPs.setString(5, user.getRole());

                return insertPs.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}