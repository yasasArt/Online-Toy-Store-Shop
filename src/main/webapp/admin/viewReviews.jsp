<%@ page import="com.toystore.model.*" %>
<%@ page import="com.toystore.service.ReviewService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    ReviewService reviewService = new ReviewService();
    List<Review> reviews = reviewService.getAllReviews();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Reviews</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">
    <aside class="sidebar">
        <h2>ToyLand Admin</h2>
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="viewReviews.jsp">Reviews</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Customer Reviews</h1>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Customer</th>
                    <th>Toy</th>
                    <th>Rating</th>
                    <th>Comment</th>
                    <th>Date</th>
                </tr>
                </thead>

                <tbody>
                <% for (Review review : reviews) { %>
                    <tr>
                        <td><%= review.getReviewId() %></td>
                        <td><%= review.getCustomerUsername() %></td>
                        <td><%= review.getToyName() %></td>
                        <td class="stars"><%= review.getStarDisplay() %></td>
                        <td><%= review.getComment() %></td>
                        <td><%= review.getReviewDate() %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </main>
</div>

</body>
</html>