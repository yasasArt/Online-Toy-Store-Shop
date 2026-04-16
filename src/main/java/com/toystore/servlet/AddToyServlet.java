package com.toystore.servlet;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/addToy")
public class AddToyServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toyId = request.getParameter("toyId");
        String toyName = request.getParameter("toyName");
        String category = request.getParameter("category");
        String ageGroup = request.getParameter("ageGroup");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Toy toy = new Toy(toyId, toyName, category, ageGroup, price, quantity);

        boolean success = toyService.addToy(toy);

        if (success) {
            response.sendRedirect("viewToys");
        } else {
            request.setAttribute("message", "Toy ID already exists.");
            request.getRequestDispatcher("addToy.jsp").forward(request, response);
        }
    }
}