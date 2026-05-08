package com.toystore.service;

import com.toystore.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String FILE_PATH = "data/users.txt";

    public UserService() {
        createFileIfNotExists();
        createDefaultAdmin();
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

    private void createDefaultAdmin() {
        if (getUserByUsername("admin") == null) {
            User admin = new User(
                    "A001",
                    "System Admin",
                    "admin@toystore.com",
                    "admin",
                    "admin123",
                    "admin",
                    "0770000000",
                    "Toy Store Head Office"
            );
            addUser(admin);
        }
    }

    public boolean addUser(User user) {
        if (getUserByUsername(user.getUsername()) != null) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.toFileString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User login(String username, String password) {
        List<User> users = getAllUsers();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                return user;
            }
        }

        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    User user = User.fromFileString(line);
                    if (user != null) {
                        users.add(user);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> getAllCustomers() {
        List<User> customers = new ArrayList<>();

        for (User user : getAllUsers()) {
            if ("customer".equalsIgnoreCase(user.getRole())) {
                customers.add(user);
            }
        }

        return customers;
    }

    public User getUserByUsername(String username) {
        for (User user : getAllUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }

        return null;
    }

    public User getUserById(String userId) {
        for (User user : getAllUsers()) {
            if (user.getUserId().equalsIgnoreCase(userId)) {
                return user;
            }
        }

        return null;
    }

    public boolean updateUser(User updatedUser) {
        List<User> users = getAllUsers();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(updatedUser.getUsername())) {
                    writer.write(updatedUser.toFileString());
                    updated = true;
                } else {
                    writer.write(user.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public boolean deleteUser(String username) {
        List<User> users = getAllUsers();
        boolean deleted = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                if (!user.getUsername().equalsIgnoreCase(username)) {
                    writer.write(user.toFileString());
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

    public String generateUserId(String role) {
        String prefix = "customer".equalsIgnoreCase(role) ? "C" : "A";
        int count = 1;

        for (User user : getAllUsers()) {
            if (user.getRole().equalsIgnoreCase(role)) {
                count++;
            }
        }

        return prefix + String.format("%03d", count);
    }
}