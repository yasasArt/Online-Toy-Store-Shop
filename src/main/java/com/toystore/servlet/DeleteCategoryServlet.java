package com.toystore.servlet;

import com.toystore.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");

        boolean success = categoryService.deleteCategory(categoryId);

        if (success) {
            request.setAttribute("message", "Category deleted successfully.");
        } else {
            request.setAttribute("message", "Category not found.");
        }

        request.getRequestDispatcher("deleteCategory.jsp").forward(request, response);
    }
}