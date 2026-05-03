<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Order</title>
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
        <h2>Delete Order</h2>
        <p class="page-subtitle">Remove an order from the system</p>
    </div>

    <div class="content-card">
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div class="alert alert-info"><%= message %></div>
        <% } %>

        <form action="deleteOrder" method="post">
            <div class="mb-3">
                <label class="form-label">Order ID</label>
                <input type="text" name="orderId" class="form-control" placeholder="Enter order ID to delete" required>
            </div>

            <button type="submit" class="btn btn-danger">Delete Order</button>
            <a href="dashboard.jsp" class="btn btn-dark">Back</a>
        </form>
    </div>
</div>

</body>
</html>