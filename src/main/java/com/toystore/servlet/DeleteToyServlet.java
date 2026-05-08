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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String toyId = request.getParameter("toyId");

        if (toyId != null && !toyId.trim().isEmpty()) {
            toyService.deleteToy(toyId);
        }

        response.sendRedirect("viewToys?msg=toyDeleted");
    }
}