package com.toystore.servlet;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/searchToy")
public class SearchToyServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toyId = request.getParameter("toyId");
        Toy toy = toyService.searchToyById(toyId);

        request.setAttribute("searched", true);
        request.setAttribute("toy", toy);
        request.getRequestDispatcher("searchToy.jsp").forward(request, response);
    }
}