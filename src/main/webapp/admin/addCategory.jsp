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
    <title>Add Category</title>
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
            <h1>Add New Category</h1>

            <% if (request.getAttribute("message") != null) { %>
                <div class="alert success"><%= request.getAttribute("message") %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/addCategory" method="post">
                <label>Category ID</label>
                <input type="text" name="categoryId" required>

                <label>Category Name</label>
                <input type="text" name="categoryName" required>

                <label>Description</label>
                <textarea name="description" required></textarea>

                <button type="submit">Add Category</button>
            </form>
        </div>
    </main>
</div>

</body>
</html>
