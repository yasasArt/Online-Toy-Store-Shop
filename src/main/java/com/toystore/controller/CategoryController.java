package com.toystore.controller;

import com.toystore.model.Category;
import com.toystore.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Handles: /viewCategories, /addCategory, /updateCategory (GET + POST), /deleteCategory
 */
@Controller
public class CategoryController {

    private final CategoryService categoryService = new CategoryService();

    // ---------------------------------------------------------------
    // VIEW CATEGORIES (GET)
    // ---------------------------------------------------------------
    @GetMapping("/viewCategories")
    public String viewCategories(@RequestParam(required = false) String keyword,
                                 Model model) {
        List<Category> categoryList = categoryService.searchCategories(keyword);
        model.addAttribute("categoryList", categoryList);
        return "admin/viewCategories";
    }

    // ---------------------------------------------------------------
    // ADD CATEGORY (POST)
    // ---------------------------------------------------------------
    @PostMapping("/addCategory")
    public String addCategory(@RequestParam String categoryId,
                              @RequestParam String categoryName,
                              @RequestParam String description,
                              Model model) {

        Category category = new Category(categoryId, categoryName, description);
        boolean success = categoryService.addCategory(category);

        if (success) {
            return "redirect:/viewCategories?msg=categoryAdded";
        } else {
            model.addAttribute("message", "Category ID already exists.");
            return "admin/addCategory";
        }
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
            return "redirect:/viewCategories?msg=categoryUpdated";
        } else {
            model.addAttribute("message", "Category not found.");
            model.addAttribute("category", category);
            return "admin/updateCategory";
        }
    }

    // ---------------------------------------------------------------
    // DELETE CATEGORY (GET)
    // ---------------------------------------------------------------
    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam String categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/viewCategories?msg=categoryDeleted";
    }
}
