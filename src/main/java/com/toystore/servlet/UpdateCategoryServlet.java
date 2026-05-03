package com.toystore.servlet;

import com.toystore.model.Category;
import com.toystore.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/updateCategory")
public class UpdateCategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        String description = request.getParameter("description");

        Category category = new Category(categoryId, categoryName, description);

        boolean success = categoryService.updateCategory(category);

        if (success) {
            request.setAttribute("message", "Category updated successfully.");
        } else {
            request.setAttribute("message", "Category not found.");
        }

        request.getRequestDispatcher("updateCategory.jsp").forward(request, response);
    }
}