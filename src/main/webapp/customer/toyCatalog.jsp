<%@ page import="java.util.List" %>
<%@ page import="com.toystore.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"customer".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<Toy> toyList = (List<Toy>) request.getAttribute("toyList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Toy Catalog</title>
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
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Toy Catalog</h1>

        <form action="../viewToys" method="get" class="search-bar">
            <input type="text" name="keyword" placeholder="Search by toy name, category, age group...">
            <button type="submit">Search</button>
        </form>

        <div class="toy-grid">
            <% if (toyList != null && !toyList.isEmpty()) {
                for (Toy toy : toyList) { %>

                <div class="toy-card">
                    <img src="<%= toy.getImageUrl() == null || toy.getImageUrl().isEmpty()
                            ? "https://cdn-icons-png.flaticon.com/512/3082/3082031.png"
                            : toy.getImageUrl() %>" alt="Toy">

                    <h3><%= toy.getToyName() %></h3>
                    <p><%= toy.getDescription() %></p>

                    <div class="toy-info">
                        <span><%= toy.getCategory() %></span>
                        <span><%= toy.getAgeGroup() %></span>
                    </div>

                    <h2>Rs. <%= toy.getPrice() %></h2>
                    <p>Available: <%= toy.getQuantity() %></p>

                    <form action="../addToCart" method="post">
                        <input type="hidden" name="toyId" value="<%= toy.getToyId() %>">
                        <input type="number" name="quantity" min="1" max="<%= toy.getQuantity() %>" value="1" required>
                        <button type="submit">Add to Cart</button>
                    </form>
                </div>

            <% }} else { %>
                <p>No toys available.</p>
            <% } %>
        </div>
    </main>
</div>

</body>
</html>