package com.toystore.service;

import com.toystore.model.User;
import com.toystore.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final String FILE_NAME = "users.txt";

    public boolean registerUser(User user) {
        if (findUserByUsername(user.getUsername()) != null) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileUtil.getFilePath(FILE_NAME), true))) {
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
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByUsername(String username) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FileUtil.getFilePath(FILE_NAME)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    User user = new User(data[0], data[1], data[2], data[3], data[4]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}