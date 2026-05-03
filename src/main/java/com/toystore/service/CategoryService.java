package com.toystore.service;

import com.toystore.model.Category;
import com.toystore.util.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static final String FILE_NAME = "categories.txt";

    public boolean addCategory(Category category) {
        if (searchCategoryById(category.getCategoryId()) != null) {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileUtil.getFilePath(FILE_NAME), true))) {
            bw.write(category.toFileString());
            bw.newLine();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FileUtil.getFilePath(FILE_NAME)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 3) {
                    Category category = new Category(
                            d[0],
                            d[1],
                            d[2]
                    );
                    categoryList.add(category);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryList;
    }

    public Category searchCategoryById(String categoryId) {
        for (Category category : getAllCategories()) {
            if (category.getCategoryId().equalsIgnoreCase(categoryId)) {
                return category;
            }
        }
        return null;
    }

    public boolean updateCategory(Category updatedCategory) {
        List<Category> categories = getAllCategories();
        boolean found = false;

        for (Category category : categories) {
            if (category.getCategoryId().equalsIgnoreCase(updatedCategory.getCategoryId())) {
                category.setCategoryName(updatedCategory.getCategoryName());
                category.setDescription(updatedCategory.getDescription());
                found = true;
                break;
            }
        }

        return found && writeAllCategories(categories);
    }

    public boolean deleteCategory(String categoryId) {
        List<Category> categories = getAllCategories();
        boolean removed = categories.removeIf(category -> category.getCategoryId().equalsIgnoreCase(categoryId));
        return removed && writeAllCategories(categories);
    }

    private boolean writeAllCategories(List<Category> categories) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FileUtil.getFilePath(FILE_NAME), false))) {
            for (Category category : categories) {
                bw.write(category.toFileString());
                bw.newLine();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}