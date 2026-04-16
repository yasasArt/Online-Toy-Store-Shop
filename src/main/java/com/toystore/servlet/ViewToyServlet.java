package com.toystore.servlet;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewToys")
public class ViewToyServlet extends HttpServlet {

    private final ToyService toyService = new ToyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Toy> toyList = toyService.getAllToys();
        request.setAttribute("toyList", toyList);
        request.getRequestDispatcher("viewToys.jsp").forward(request, response);
    }
}