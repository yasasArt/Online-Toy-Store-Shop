package com.toystore.service;

import com.toystore.model.Toy;
import com.toystore.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ToyService {

    private static final String FILE_NAME = "toys.txt";

    public boolean addToy(Toy toy) {
        if (searchToyById(toy.getToyId()) != null) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileUtil.getFilePath(FILE_NAME), true))) {
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

        try (BufferedReader reader = new BufferedReader(new FileReader(FileUtil.getFilePath(FILE_NAME)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    Toy toy = new Toy(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            Double.parseDouble(data[4]),
                            Integer.parseInt(data[5])
                    );
                    toys.add(toy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toys;
    }

    public Toy searchToyById(String toyId) {
        List<Toy> toys = getAllToys();
        for (Toy toy : toys) {
            if (toy.getToyId().equalsIgnoreCase(toyId)) {
                return toy;
            }
        }
        return null;
    }

    public boolean updateToy(Toy updatedToy) {
        List<Toy> toys = getAllToys();
        boolean found = false;

        for (Toy toy : toys) {
            if (toy.getToyId().equalsIgnoreCase(updatedToy.getToyId())) {
                toy.setToyName(updatedToy.getToyName());
                toy.setCategory(updatedToy.getCategory());
                toy.setAgeGroup(updatedToy.getAgeGroup());
                toy.setPrice(updatedToy.getPrice());
                toy.setQuantity(updatedToy.getQuantity());
                found = true;
                break;
            }
        }

        if (found) {
            return writeAllToys(toys);
        }
        return false;
    }

    public boolean deleteToy(String toyId) {
        List<Toy> toys = getAllToys();
        boolean removed = toys.removeIf(toy -> toy.getToyId().equalsIgnoreCase(toyId));
        if (removed) {
            return writeAllToys(toys);
        }
        return false;
    }

    private boolean writeAllToys(List<Toy> toys) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileUtil.getFilePath(FILE_NAME), false))) {
            for (Toy toy : toys) {
                writer.write(toy.toFileString());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}