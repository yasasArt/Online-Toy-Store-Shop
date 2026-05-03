<%@ page import="java.util.List,com.toystore.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Categories</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-custom">
    <div class="container">
        <a class="navbar-brand" href="dashboard.jsp">
            <i class="bi bi-controller"></i> Online Toy Store
        </a>
        <div>
            <a href="dashboard.jsp" class="btn btn-outline-light btn-sm">Dashboard</a>
        </div>
    </div>
</nav>

<div class="container py-4">
    <div class="page-header">
        <h2>Category List</h2>
        <p class="page-subtitle">View all toy categories</p>
    </div>

    <div class="table-wrapper">
        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead>
                <tr>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
                    if (categoryList != null && !categoryList.isEmpty()) {
                        for (Category c : categoryList) {
                %>
                <tr>
                    <td><%= c.getCategoryId() %></td>
                    <td><%= c.getCategoryName() %></td>
                    <td><%= c.getDescription() %></td>
                </tr>
                <%      }
                    } else { %>
                <tr>
                    <td colspan="3" class="text-center">No categories found.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <a href="dashboard.jsp" class="btn btn-dark mt-3">Back</a>
    </div>
</div>

</body>
</html>