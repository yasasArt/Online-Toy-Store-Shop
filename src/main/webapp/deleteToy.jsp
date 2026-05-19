<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Toy</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap + Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

    <style>
        body {
            background: linear-gradient(135deg, #ffe4ec, #e0f7ff, #fff7d6);
            
            font-family: 'Segoe UI', sans-serif;
        }

        .navbar {
            background: linear-gradient(90deg, #ff6b6b, #ff9f43);
        }

        .navbar-brand {
            font-weight: bold;
            color: white !important;
        }

        .delete-card {
            background: #fff;
            border-radius: 20px;
            padding: 30px;
            margin-top: 40px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            text-align: center;
            animation: fadeIn 0.5s ease;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(20px);}
            to {opacity: 1; transform: translateY(0);}
        }

        .icon-danger {
            font-size: 60px;
            color: #dc3545;
        }

        .form-control {
            border-radius: 10px;
        }

        .btn-custom {
            border-radius: 12px;
            font-weight: bold;
            padding: 10px;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand"><i class="bi bi-controller"></i> Toy Store</a>
        <div class="ms-auto">
            <a href="dashboard" class="btn btn-light me-2">Dashboard</a>
            <a href="logout" class="btn btn-dark">Logout</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="delete-card">

        <div class="icon-danger mb-3">
            <i class="bi bi-exclamation-triangle-fill"></i>
        </div>

        <h3 class="text-danger fw-bold">Delete Toy</h3>
        <p class="text-muted">This action cannot be undone. Please enter the Toy ID carefully.</p>

        <!-- Message -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div class="alert alert-danger text-center"><%= message %></div>
        <% } %>

        <form action="deleteToy" method="post" onsubmit="return confirmDelete()">

            <div class="mb-3">
                <label class="form-label fw-bold">Toy ID</label>
                <input type="text" name="toyId" class="form-control text-center" placeholder="Enter Toy ID" required>
            </div>

            <div class="d-flex gap-2 mt-3">
                <button type="submit" class="btn btn-danger btn-custom w-100">
                    <i class="bi bi-trash-fill"></i> Delete
                </button>

                <a href="dashboard" class="btn btn-secondary btn-custom w-100">
                    <i class="bi bi-arrow-left"></i> Cancel
                </a>
            </div>

        </form>

    </div>
</div>

<script>
    function confirmDelete() {
        return confirm("Are you sure you want to delete this toy?");
    }
</script>

</body>
</html>