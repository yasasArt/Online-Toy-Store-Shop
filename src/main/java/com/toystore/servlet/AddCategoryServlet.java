package com.toystore.servlet;

import com.toystore.model.Category;
import com.toystore.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/addCategory")
public class AddCategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        String description = request.getParameter("description");

        Category category = new Category(categoryId, categoryName, description);

        boolean success = categoryService.addCategory(category);

        if (success) {
            request.setAttribute("message", "Category added successfully.");
        } else {
            request.setAttribute("message", "Category ID already exists.");
        }

        request.getRequestDispatcher("addCategory.jsp").forward(request, response);
    }
}