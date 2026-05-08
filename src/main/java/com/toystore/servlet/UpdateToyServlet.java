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
        Toy toy = toyService.getToyById(toyId);

        if (toy != null) {
            request.setAttribute("toy", toy);
            request.getRequestDispatcher("admin/editToy.jsp").forward(request, response);
        } else {
            response.sendRedirect("viewToys?error=toyNotFound");
        }
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
            response.sendRedirect("viewToys?msg=toyUpdated");
        } else {
            response.sendRedirect("viewToys?error=updateFailed");
        }
    }
}