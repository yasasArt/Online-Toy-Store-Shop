package com.toystore.service;

import com.toystore.model.Toy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToyService {
    private static final String FILE_PATH = "data/toys.txt";

    public ToyService() {
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

    public boolean addToy(Toy toy) {
        if (getToyById(toy.getToyId()) != null) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(toy.toFileString());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Toy> getAllToys() {
        List<Toy> toys = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Toy toy = Toy.fromFileString(line);
                    if (toy != null) {
                        toys.add(toy);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return toys;
    }

    public Toy getToyById(String toyId) {
        for (Toy toy : getAllToys()) {
            if (toy.getToyId().equalsIgnoreCase(toyId)) {
                return toy;
            }
        }

        return null;
    }

    public List<Toy> searchToys(String keyword) {
        List<Toy> result = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllToys();
        }

        String search = keyword.toLowerCase();

        for (Toy toy : getAllToys()) {
            if (toy.getToyName().toLowerCase().contains(search)
                    || toy.getCategory().toLowerCase().contains(search)
                    || toy.getAgeGroup().toLowerCase().contains(search)
                    || toy.getBrand().toLowerCase().contains(search)) {
                result.add(toy);
            }
        }

        return result;
    }

    public boolean updateToy(Toy updatedToy) {
        List<Toy> toys = getAllToys();
        boolean updated = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Toy toy : toys) {
                if (toy.getToyId().equalsIgnoreCase(updatedToy.getToyId())) {
                    writer.write(updatedToy.toFileString());
                    updated = true;
                } else {
                    writer.write(toy.toFileString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public boolean deleteToy(String toyId) {
        List<Toy> toys = getAllToys();
        boolean deleted = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Toy toy : toys) {
                if (!toy.getToyId().equalsIgnoreCase(toyId)) {
                    writer.write(toy.toFileString());
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

    public boolean reduceStock(String toyId, int quantity) {
        Toy toy = getToyById(toyId);

        if (toy == null || toy.getQuantity() < quantity) {
            return false;
        }

        toy.setQuantity(toy.getQuantity() - quantity);
        return updateToy(toy);
    }

    public String generateToyId() {
        int count = getAllToys().size() + 1;
        return "T" + String.format("%03d", count);
    }
}