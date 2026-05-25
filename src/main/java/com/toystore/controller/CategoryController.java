package com.toystore.controller;

import com.toystore.model.Category;
import com.toystore.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Handles: /addCategory, /updateCategory (GET + POST)
 * Replaces: AddCategoryServlet, UpdateCategoryServlet
 */
@Controller
public class CategoryController {

    private final CategoryService categoryService = new CategoryService();

    // ---------------------------------------------------------------
    // ADD CATEGORY
    // ---------------------------------------------------------------
    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String categoryId,
                              @RequestParam String categoryName,
                              @RequestParam String description,
                              Model model) {

        Category category = new Category(categoryId, categoryName, description);
        boolean success = categoryService.addCategory(category);

        if (success) {
            model.addAttribute("message", "Category added successfully.");
        } else {
            model.addAttribute("message", "Category ID already exists.");
        }

        return "admin/addCategory";
    }

    // ---------------------------------------------------------------
    // EDIT CATEGORY FORM (GET)
    // ---------------------------------------------------------------
    @GetMapping("/updateCategory")
    public String editCategoryForm(@RequestParam(required = false) String categoryId,
                                   Model model) {

        if (categoryId != null && !categoryId.trim().isEmpty()) {
            Category category = categoryService.searchCategoryById(categoryId);
            model.addAttribute("category", category);
            if (category == null) {
                model.addAttribute("message", "Category not found.");
            }
        }
        return "admin/updateCategory";
    }

    // ---------------------------------------------------------------
    // SAVE UPDATED CATEGORY (POST)
    // ---------------------------------------------------------------
    @PostMapping("/updateCategory")
    public String updateCategory(@RequestParam String categoryId,
                                 @RequestParam String categoryName,
                                 @RequestParam String description,
                                 Model model) {

        Category category = new Category(categoryId, categoryName, description);
        boolean success = categoryService.updateCategory(category);

        if (success) {
            model.addAttribute("message", "Category updated successfully.");
            model.addAttribute("category", category);
        } else {
            model.addAttribute("message", "Category not found.");
        }

        return "admin/updateCategory";
    }
}
