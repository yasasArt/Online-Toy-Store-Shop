<%@ page import="com.toystore.model.User" %>
<%@ page import="com.toystore.model.Category" %>
<%@ page import="com.toystore.service.CategoryService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
    if (loggedUser == null || !"admin".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    CategoryService categoryService = new CategoryService();
    List<Category> categoryList = categoryService.getAllCategories();
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
                        <label>Category <span style="font-weight:normal; font-size:12px;">(<a href="addCategory.jsp">Add New</a>)</span></label>
                        <select name="category" required>
                            <option value="">Select Category</option>
                            <% if (categoryList != null) {
                                for (Category cat : categoryList) { %>
                                    <option value="<%= cat.getCategoryName() %>"><%= cat.getCategoryName() %></option>
                            <%  }
                               } %>
                        </select>
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