package com.toystore.servlet;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/updateToy")
public class UpdateToyServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String toyId = request.getParameter("toyId");

        if (toyId == null || toyId.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/viewToys?error=noToyId");
            return;
        }

        Toy toy = toyService.getToyById(toyId);

        if (toy == null) {
            response.sendRedirect(request.getContextPath() + "/viewToys?error=toyNotFound");
            return;
        }

        request.setAttribute("toy", toy);
        request.getRequestDispatcher("/admin/editToy.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String toyId = request.getParameter("toyId");
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

        boolean success = toyService.updateToy(toy);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/viewToys?msg=toyUpdated");
        } else {
            response.sendRedirect(request.getContextPath() + "/viewToys?error=updateFailed");
        }
    }
}