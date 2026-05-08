<%@ page import="java.util.List" %>
<%@ page import="com.toystore.model.Toy" %>
<%@ page import="com.toystore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    List<Toy> toyList = (List<Toy>) request.getAttribute("toyList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Toys</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="dashboard-layout">
    <aside class="sidebar">
        <h2>ToyLand Admin</h2>
        <a href="adminDashboard.jsp">Dashboard</a>
        <a href="addToy.jsp">Add Toy</a>
        <a href="../viewToys">Manage Toys</a>
        <a href="../logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <h1>Manage Toys</h1>

        <form action="../viewToys" method="get" class="search-bar">
            <input type="text" name="keyword" placeholder="Search toys...">
            <button type="submit">Search</button>
        </form>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Toy</th>
                    <th>Category</th>
                    <th>Age</th>
                    <th>Brand</th>
                    <th>Price</th>
                    <th>Qty</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <% if (toyList != null && !toyList.isEmpty()) {
                    for (Toy toy : toyList) { %>
                    <tr>
                        <td><%= toy.getToyId() %></td>
                        <td><%= toy.getToyName() %></td>
                        <td><%= toy.getCategory() %></td>
                        <td><%= toy.getAgeGroup() %></td>
                        <td><%= toy.getBrand() %></td>
                        <td>Rs. <%= toy.getPrice() %></td>
                        <td><%= toy.getQuantity() %></td>
                        <td>
                            <a class="edit-btn" href="../updateToy?toyId=<%= toy.getToyId() %>">Edit</a>
                            <a class="delete-btn" href="../deleteToy?toyId=<%= toy.getToyId() %>"
                               onclick="return confirm('Delete this toy?')">Delete</a>
                        </td>
                    </tr>
                <% }} else { %>
                    <tr>
                        <td colspan="8">No toys found.</td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </main>
</div>

</body>
</html>