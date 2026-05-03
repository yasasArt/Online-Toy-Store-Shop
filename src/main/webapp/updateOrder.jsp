<%@ page import="com.toystore.model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Order</title>
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
        <h2>Update Order</h2>
        <p class="page-subtitle">Search an order and update its details</p>
    </div>

    <div class="content-card mb-4">
        <%
            String message = (String) request.getAttribute("message");
            Order order = (Order) request.getAttribute("order");
            if (message != null) {
        %>
        <div class="alert alert-info"><%= message %></div>
        <% } %>

        <h5 class="mb-3">Find Order</h5>
        <form action="updateOrder" method="get" class="row g-3">
            <div class="col-md-9">
                <label class="form-label">Order ID</label>
                <input type="text" name="orderId" class="form-control" placeholder="Enter order ID to search" required>
            </div>
            <div class="col-md-3 d-flex align-items-end">
                <button type="submit" class="btn btn-primary w-100">Search</button>
            </div>
        </form>
    </div>

    <div class="content-card">
        <h5 class="mb-3">Order Details</h5>

        <form action="updateOrder" method="post">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Order ID</label>
                    <input type="text" name="orderId" class="form-control"
                           value="<%= order != null ? order.getOrderId() : "" %>" readonly required>
                </div>

                <div class="col-md-6 mb-3">
                    <label class="form-label">Customer Name</label>
                    <input type="text" name="customerName" class="form-control"
                           value="<%= order != null ? order.getCustomerName() : "" %>"
                           placeholder="Customer name" required>
                </div>

                <div class="col-md-6 mb-3">
                    <label class="form-label">Toy ID</label>
                    <input type="text" name="toyId" class="form-control"
                           value="<%= order != null ? order.getToyId() : "" %>"
                           placeholder="Toy ID" required>
                </div>

                <div class="col-md-6 mb-3">
                    <label class="form-label">Quantity</label>
                    <input type="number" name="quantity" class="form-control"
                           value="<%= order != null ? order.getQuantity() : "" %>"
                           placeholder="Quantity" required>
                </div>

                <div class="col-md-12 mb-3">
                    <label class="form-label">Status</label>
                    <select name="status" class="form-select" required>
                        <option value="">Select status</option>
                        <option value="Pending" <%= order != null && "Pending".equals(order.getStatus()) ? "selected" : "" %>>Pending</option>
                        <option value="Processing" <%= order != null && "Processing".equals(order.getStatus()) ? "selected" : "" %>>Processing</option>
                        <option value="Completed" <%= order != null && "Completed".equals(order.getStatus()) ? "selected" : "" %>>Completed</option>
                        <option value="Cancelled" <%= order != null && "Cancelled".equals(order.getStatus()) ? "selected" : "" %>>Cancelled</option>
                    </select>
                </div>
            </div>

            <button type="submit" class="btn btn-info">Update Order</button>
            <a href="dashboard.jsp" class="btn btn-dark">Back</a>
        </form>
    </div>
</div>

</body>
</html>