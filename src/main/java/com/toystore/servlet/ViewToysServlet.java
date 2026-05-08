package com.toystore.servlet;

import com.toystore.model.Toy;
import com.toystore.service.ToyService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewToys")
public class ViewToysServlet extends HttpServlet {
    private final ToyService toyService = new ToyService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        List<Toy> toyList = toyService.searchToys(keyword);

        request.setAttribute("toyList", toyList);

        HttpSession session = request.getSession(false);
        String role = session != null ? (String) session.getAttribute("role") : null;

        if ("admin".equalsIgnoreCase(role)) {
            request.getRequestDispatcher("admin/viewToys.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("customer/toyCatalog.jsp").forward(request, response);
        }
    }
}