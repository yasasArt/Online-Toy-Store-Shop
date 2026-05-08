<%@ page import="com.toystore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Toy</title>
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
        <div class="form-panel">
            <h1>Add New Toy</h1>

            <% if (request.getAttribute("error") != null) { %>
                <div class="alert error"><%= request.getAttribute("error") %></div>
            <% } %>

            <form action="../addToy" method="post">
                <div class="grid-2">
                    <div>
                        <label>Toy ID</label>
                        <input type="text" name="toyId" placeholder="Leave empty for auto ID">
                    </div>

                    <div>
                        <label>Toy Name</label>
                        <input type="text" name="toyName" required>
                    </div>

                    <div>
                        <label>Category</label>
                        <input type="text" name="category" placeholder="Soft Toy / Puzzle / Car" required>
                    </div>

                    <div>
                        <label>Age Group</label>
                        <input type="text" name="ageGroup" placeholder="3-5 years" required>
                    </div>

                    <div>
                        <label>Brand</label>
                        <input type="text" name="brand" required>
                    </div>

                    <div>
                        <label>Price</label>
                        <input type="number" step="0.01" name="price" required>
                    </div>

                    <div>
                        <label>Quantity</label>
                        <input type="number" name="quantity" required>
                    </div>

                    <div>
                        <label>Image URL</label>
                        <input type="text" name="imageUrl" placeholder="https://...">
                    </div>
                </div>

                <label>Description</label>
                <textarea name="description" required></textarea>

                <button type="submit">Save Toy</button>
            </form>
        </div>
    </main>
</div>

</body>
</html>