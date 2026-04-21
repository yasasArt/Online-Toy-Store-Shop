<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Toy</title>
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
            font-size: 1.5rem;
        }

        .form-card {
            background: #fff;
            border-radius: 20px;
            padding: 30px;
            margin-top: 40px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            animation: fadeIn 0.5s ease;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(20px);}
            to {opacity: 1; transform: translateY(0);}
        }

        .form-title {
            font-weight: bold;
            color: #ff6b6b;
        }

        .form-control {
            border-radius: 10px;
        }

        .input-group-text {
            border-radius: 10px 0 0 10px;
        }

        .btn-custom {
            border-radius: 12px;
            font-weight: bold;
            padding: 10px;
        }

        .preview-box {
            border: 2px dashed #ddd;
            border-radius: 15px;
            padding: 15px;
            text-align: center;
            margin-top: 10px;
        }

        .preview-box img {
            max-width: 100%;
            height: 150px;
            object-fit: contain;
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
    <div class="form-card">

        <h3 class="form-title mb-3"><i class="bi bi-plus-circle-fill"></i> Add New Toy</h3>

        <!-- Message -->
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
        %>
        <div class="alert alert-danger text-center"><%= message %></div>
        <% } %>

        <form action="addToy" method="post">

            <div class="row g-3">

                <div class="col-md-6">
                    <label class="form-label">Toy ID</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-hash"></i></span>
                        <input type="text" name="toyId" class="form-control" placeholder="Enter ID" required>
                    </div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Toy Name</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-box-seam"></i></span>
                        <input type="text" name="toyName" class="form-control" placeholder="Enter toy name" required>
                    </div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Category</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-tags"></i></span>
                        <input type="text" name="category" class="form-control" placeholder="e.g. Educational, Electronic" required>
                    </div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Age Group</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-emoji-smile"></i></span>
                        <input type="text" name="ageGroup" class="form-control" placeholder="e.g. 3-5, 6-10" required>
                    </div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Price</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-currency-dollar"></i></span>
                        <input type="number" step="0.01" name="price" class="form-control" placeholder="Enter price" required>
                    </div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Quantity</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="bi bi-stack"></i></span>
                        <input type="number" name="quantity" class="form-control" placeholder="Enter quantity" required>
                    </div>
                </div>

            </div>

            <div class="d-flex gap-2 mt-4">
                <button type="submit" class="btn btn-success btn-custom w-100">
                    <i class="bi bi-check-circle-fill"></i> Add Toy
                </button>
                <a href="dashboard" class="btn btn-secondary btn-custom w-100">
                    <i class="bi bi-arrow-left"></i> Back
                </a>
            </div>

        </form>

    </div>
</div>

</body>
</html>