<%@ page import="com.toystore.model.User" %>
<%@ page import="com.toystore.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }
    Category category = (Category) request.getAttribute("category");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Category</title>
    <link rel="stylesheet" href="../css/style.css">
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
            <h1>Search &amp; Update Category</h1>

            <% if (request.getAttribute("message") != null) { %>
                <div class="alert success"><%= request.getAttribute("message") %></div>
            <% } %>

            <!-- Search form -->
            <form action="${pageContext.request.contextPath}/updateCategory" method="get">
                <label>Category ID</label>
                <input type="text" name="categoryId" value="<%= category != null ? category.getCategoryId() : "" %>" required>
                <button type="submit">Search</button>
            </form>

            <!-- Update form (only shown when category is found) -->
            <% if (category != null) { %>
            <form action="${pageContext.request.contextPath}/updateCategory" method="post" style="margin-top:20px;">
                <input type="hidden" name="categoryId" value="<%= category.getCategoryId() %>">

                <label>Category Name</label>
                <input type="text" name="categoryName" value="<%= category.getCategoryName() %>" required>

                <label>Description</label>
                <textarea name="description" required><%= category.getDescription() %></textarea>

                <button type="submit">Update Category</button>
            </form>
            <% } %>
        </div>
    </main>
</div>

</body>
</html>
