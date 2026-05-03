<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Order</title>
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
        <h2>Add New Order</h2>
        <p class="page-subtitle">Create a new customer order</p>
    </div>

    <div class="content-card">
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div class="alert alert-info"><%= message %></div>
        <% } %>

        <form action="addOrder" method="post">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Order ID</label>
                    <input type="text" name="orderId" class="form-control" placeholder="Enter order ID" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Customer Name</label>
                    <input type="text" name="customerName" class="form-control" placeholder="Enter customer name" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Toy ID</label>
                    <input type="text" name="toyId" class="form-control" placeholder="Enter toy ID" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label class="form-label">Quantity</label>
                    <input type="number" name="quantity" class="form-control" placeholder="Enter quantity" required>
                </div>
                <div class="col-md-12 mb-3">
                    <label class="form-label">Status</label>
                    <select name="status" class="form-select" required>
                        <option value="">Select status</option>
                        <option value="Pending">Pending</option>
                        <option value="Processing">Processing</option>
                        <option value="Completed">Completed</option>
                        <option value="Cancelled">Cancelled</option>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Add Order</button>
            <a href="dashboard.jsp" class="btn btn-dark">Back</a>
        </form>
    </div>
</div>

</body>
</html>