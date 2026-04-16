package com.toystore.servlet;

import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteToy")
public class DeleteToyServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toyId = request.getParameter("toyId");

        boolean success = toyService.deleteToy(toyId);

        if (success) {
            response.sendRedirect("viewToys");
        } else {
            request.setAttribute("message", "Toy not found.");
            request.getRequestDispatcher("deleteToy.jsp").forward(request, response);
        }
    }
}