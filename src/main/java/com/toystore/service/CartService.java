package com.toystore.service;

import com.toystore.model.CartItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private static final String FILE_PATH = "data/cart.txt";

    public CartService() {
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

    public boolean addToCart(CartItem item) {
        CartItem existingItem = getCartItem(item.getCustomerUsername(), item.getToyId());

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            return updateCartItem(existingItem);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(item.toFileString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<CartItem> getAllCartItems() {
        List<CartItem> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    CartItem item = CartItem.fromFileString(line);
                    if (item != null) {
                        items.add(item);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    public List<CartItem> getCartByCustomer(String username) {
        List<CartItem> customerCart = new ArrayList<>();

        for (CartItem item : getAllCartItems()) {
            if (item.getCustomerUsername().equalsIgnoreCase(username)) {
                customerCart.add(item);
            }
        }

        return customerCart;
    }

    public CartItem getCartItem(String username, String toyId) {
        for (CartItem item : getAllCartItems()) {
            if (item.getCustomerUsername().equalsIgnoreCase(username)
                    && item.getToyId().equalsIgnoreCase(toyId)) {
                return item;
            }
        }

        return null;
    }

    public boolean updateCartItem(CartItem updatedItem) {
        List<CartItem> items = getAllCartItems();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (CartItem item : items) {
                if (item.getCartId().equalsIgnoreCase(updatedItem.getCartId())) {
                    writer.write(updatedItem.toFileString());
                    updated = true;
                } else {
                    writer.write(item.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public boolean removeCartItem(String cartId) {
        List<CartItem> items = getAllCartItems();
        boolean removed = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (CartItem item : items) {
                if (!item.getCartId().equalsIgnoreCase(cartId)) {
                    writer.write(item.toFileString());
                    writer.newLine();
                } else {
                    removed = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return removed;
    }

    public boolean clearCustomerCart(String username) {
        List<CartItem> items = getAllCartItems();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (CartItem item : items) {
                if (!item.getCustomerUsername().equalsIgnoreCase(username)) {
                    writer.write(item.toFileString());
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public double getCartTotal(String username) {
        double total = 0;

        for (CartItem item : getCartByCustomer(username)) {
            total += item.getTotal();
        }

        return total;
    }

    public String generateCartId() {
        int count = getAllCartItems().size() + 1;
        return "CART" + String.format("%03d", count);
    }
}