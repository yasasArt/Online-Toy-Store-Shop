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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String toyId = request.getParameter("toyId");

        if (toyId == null || toyId.trim().isEmpty()) {
            toyId = toyService.generateToyId();
        }

        String toyName = request.getParameter("toyName");
        String category = request.getParameter("category");
        String ageGroup = request.getParameter("ageGroup");
        String brand = request.getParameter("brand");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");

        Toy toy = new Toy(
                toyId,
                toyName,
                category,
                ageGroup,
                brand,
                price,
                quantity,
                description,
                imageUrl
        );

        boolean success = toyService.addToy(toy);

        if (success) {
            response.sendRedirect("viewToys?msg=toyAdded");
        } else {
            request.setAttribute("error", "Toy ID already exists!");
            request.getRequestDispatcher("admin/addToy.jsp").forward(request, response);
        }
    }
}