<%@ page import="java.util.List" %>
<%@ page import="com.toystore.model.Category" %>
<%@ page import="com.toystore.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");

    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }

    List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Manage Categories</title>
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
        <h1>Manage Categories</h1>
        <p class="muted">View, update, and delete toy categories.</p>

        <%
            String msg = request.getParameter("msg");
            if (msg != null) {
                String displayMsg = "";
                if ("categoryAdded".equals(msg)) displayMsg = "Category added successfully.";
                else if ("categoryUpdated".equals(msg)) displayMsg = "Category updated successfully.";
                else if ("categoryDeleted".equals(msg)) displayMsg = "Category deleted successfully.";
                
                if (!displayMsg.isEmpty()) {
        %>
                    <div class="alert success" style="margin-bottom: 20px;"><%= displayMsg %></div>
        <%
                }
            }
        %>

        <form action="${pageContext.request.contextPath}/viewCategories" method="get" class="search-bar">
            <input type="text" name="keyword" placeholder="Search categories...">
            <button type="submit">Search</button>
        </form>

        <div class="table-panel">
            <table>
                <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Description</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <% if (categoryList != null && !categoryList.isEmpty()) {
                    for (Category category : categoryList) { %>

                    <tr>
                        <td><%= category.getCategoryId() %></td>
                        <td><%= category.getCategoryName() %></td>
                        <td><%= category.getDescription() %></td>
                        <td>
                            <a class="edit-btn"
                               href="${pageContext.request.contextPath}/updateCategory?categoryId=<%= category.getCategoryId() %>">
                                Edit
                            </a>

                            <a class="delete-btn"
                               href="${pageContext.request.contextPath}/deleteCategory?categoryId=<%= category.getCategoryId() %>"
                               onclick="return confirm('Are you sure you want to delete this category?');">
                                Delete
                            </a>
                        </td>
                    </tr>

                <% }} else { %>

                    <tr>
                        <td colspan="4">No categories found.</td>
                    </tr>

                <% } %>
                </tbody>
            </table>
        </div>
    </main>

</div>

</body>
</html>
