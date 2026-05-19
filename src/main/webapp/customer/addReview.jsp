<%@ page import="com.toystore.model.*" %>
<%@ page import="com.toystore.service.ToyService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");

    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    ToyService toyService = new ToyService();
    List<Toy> toys = toyService.getAllToys();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Review</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">

    <aside class="sidebar customer-side">
        <h2>ToyLand</h2>

        <a href="customerDashboard.jsp">Dashboard</a>
        <a href="../viewToys">Toy Catalog</a>
        <a href="cart.jsp">My Cart</a>
        <a href="myOrders.jsp">My Orders</a>
        <a href="addReview.jsp">Add Review</a>
        <a href="profile.jsp">Profile</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Add Review</h1>
        <p class="muted">Share your feedback about toys.</p>

        <% if ("success".equals(request.getParameter("msg"))) { %>
            <div class="alert success">Review added successfully.</div>
        <% } %>

        <% if (request.getParameter("error") != null) { %>
            <div class="alert error">Review adding failed. Try again.</div>
        <% } %>

        <div class="form-panel">
            <form action="${pageContext.request.contextPath}/addReview" method="post">

                <label>Select Toy</label>
                <select name="toyId" required>
                    <option value="">-- Select Toy --</option>
                    <% for (Toy toy : toys) { %>
                        <option value="<%= toy.getToyId() %>">
                            <%= toy.getToyName() %>
                        </option>
                    <% } %>
                </select>

                <label>Rating</label>
                <select name="rating" required>
                    <option value="5">5 - Excellent</option>
                    <option value="4">4 - Very Good</option>
                    <option value="3">3 - Good</option>
                    <option value="2">2 - Average</option>
                    <option value="1">1 - Poor</option>
                </select>

                <label>Comment</label>
                <textarea name="comment" placeholder="Write your review..." required></textarea>

                <button type="submit">Submit Review</button>
            </form>
        </div>
    </main>

</div>

</body>
</html>