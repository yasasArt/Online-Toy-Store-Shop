package com.toystore.servlet;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/viewToys")
public class ViewToyServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Toy> toyList = toyService.getAllToys();

        String search = request.getParameter("search");
        List<Toy> filteredList = new ArrayList<>();

        if (search != null && !search.trim().isEmpty()) {
            String keyword = search.trim().toLowerCase();

            for (Toy toy : toyList) {
                if (toy.getToyId().toLowerCase().contains(keyword) ||
                        toy.getToyName().toLowerCase().contains(keyword) ||
                        toy.getCategory().toLowerCase().contains(keyword) ||
                        toy.getAgeGroup().toLowerCase().contains(keyword)) {
                    filteredList.add(toy);
                }
            }

            request.setAttribute("toyList", filteredList);
        } else {
            request.setAttribute("toyList", toyList);
        }

        request.setAttribute("search", search);
        request.getRequestDispatcher("viewToys.jsp").forward(request, response);
    }
}