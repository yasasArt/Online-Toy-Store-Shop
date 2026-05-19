<%@ page import="com.toystore.model.Toy" %>
<%@ page import="com.toystore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");

    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    Toy toy = (Toy) request.getAttribute("toy");

    if (toy == null) {
        response.sendRedirect(request.getContextPath() + "/viewToys");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Toy</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="dashboard-layout">

    <aside class="sidebar">
        <h2>ToyLand Admin</h2>

        <a href="${pageContext.request.contextPath}/admin/adminDashboard.jsp">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin/addToy.jsp">Add Toy</a>
        <a href="${pageContext.request.contextPath}/viewToys">Manage Toys</a>
        <a href="${pageContext.request.contextPath}/admin/addCategory.jsp">Add Category</a>
        <a href="${pageContext.request.contextPath}/viewCategories">Manage Categories</a>
        <a href="${pageContext.request.contextPath}/admin/viewCustomers.jsp">Customers</a>
        <a href="${pageContext.request.contextPath}/admin/viewOrders.jsp">Orders</a>
        <a href="${pageContext.request.contextPath}/admin/viewPayments.jsp">Payments</a>
        <a href="${pageContext.request.contextPath}/admin/viewReviews.jsp">Reviews</a>

        <a href="${pageContext.request.contextPath}/logout" class="logout">Logout</a>
    </aside>

    <main class="content">
        <div class="form-panel">
            <h1>Edit Toy</h1>
            <p class="muted">Update selected toy details.</p>

            <form action="${pageContext.request.contextPath}/updateToy" method="post">
                <input type="hidden" name="toyId" value="<%= toy.getToyId() %>">

                <div class="grid-2">
                    <div>
                        <label>Toy Name</label>
                        <input type="text" name="toyName" value="<%= toy.getToyName() %>" required>
                    </div>

                    <div>
                        <label>Category</label>
                        <input type="text" name="category" value="<%= toy.getCategory() %>" required>
                    </div>

                    <div>
                        <label>Age Group</label>
                        <input type="text" name="ageGroup" value="<%= toy.getAgeGroup() %>" required>
                    </div>

                    <div>
                        <label>Brand</label>
                        <input type="text" name="brand" value="<%= toy.getBrand() %>" required>
                    </div>

                    <div>
                        <label>Price</label>
                        <input type="number" step="0.01" name="price" value="<%= toy.getPrice() %>" required>
                    </div>

                    <div>
                        <label>Quantity</label>
                        <input type="number" name="quantity" value="<%= toy.getQuantity() %>" required>
                    </div>

                    <div>
                        <label>Image URL</label>
                        <input type="text" name="imageUrl" value="<%= toy.getImageUrl() %>">
                    </div>
                </div>

                <label>Description</label>
                <textarea name="description" required><%= toy.getDescription() %></textarea>

                <button type="submit">Update Toy</button>
            </form>
        </div>
    </main>

</div>

</body>
</html>