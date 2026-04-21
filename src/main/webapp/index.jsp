<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Toy Store</title>
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

        .hero {
            text-align: center;
            padding: 80px 20px;
        }

        .hero img {
            width: 150px;
            margin-bottom: 20px;
        }

        .hero h1 {
            font-weight: bold;
            color: #ff6b6b;
        }

        .hero p {
            color: #555;
            font-size: 18px;
        }

        .btn-custom {
            border-radius: 30px;
            padding: 12px 25px;
            font-weight: bold;
            margin: 10px;
        }

        .features {
            margin-top: 50px;
        }

        .feature-card {
            border: none;
            border-radius: 20px;
            padding: 25px;
            text-align: center;
            box-shadow: 0 8px 25px rgba(0,0,0,0.08);
            transition: 0.3s;
        }

        .feature-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 12px 35px rgba(0,0,0,0.15);
        }

        .feature-icon {
            font-size: 2.5rem;
            margin-bottom: 10px;
        }

        .footer {
            text-align: center;
            margin-top: 60px;
            padding: 20px;
            color: #777;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg">
    <div class="container">
        <a class="navbar-brand">
            <i class="bi bi-controller"></i> Toy Store
        </a>
        <div class="ms-auto">
            <a href="login.jsp" class="btn btn-light me-2">Login</a>
            <a href="register.jsp" class="btn btn-dark">Register</a>
        </div>
    </div>
</nav>

<!-- Hero Section -->
<div class="hero">
    <img src="https://cdn-icons-png.flaticon.com/512/3081/3081559.png">
    <h1>Welcome to Online Toy Store 🧸</h1>
    <p>Discover, manage, and enjoy toys in a simple and smart way.</p>

    <div>
        <a href="register.jsp" class="btn btn-primary btn-custom">
            <i class="bi bi-person-plus-fill"></i> Get Started
        </a>
        <a href="login.jsp" class="btn btn-success btn-custom">
            <i class="bi bi-box-arrow-in-right"></i> Login
        </a>
        <a href="viewToys" class="btn btn-warning btn-custom">
            <i class="bi bi-bag-fill"></i> Browse Toys
        </a>
    </div>
</div>

<!-- Features -->
<div class="container features">
    <div class="row g-4">

        <div class="col-md-4">
            <div class="feature-card">
                <div class="feature-icon text-primary">
                    <i class="bi bi-bag-check-fill"></i>
                </div>
                <h5>Manage Toys</h5>
                <p class="text-muted">Add, update, and delete toys easily.</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="feature-card">
                <div class="feature-icon text-success">
                    <i class="bi bi-people-fill"></i>
                </div>
                <h5>User System</h5>
                <p class="text-muted">Register and manage user accounts.</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="feature-card">
                <div class="feature-icon text-warning">
                    <i class="bi bi-cart-fill"></i>
                </div>
                <h5>Easy Shopping</h5>
                <p class="text-muted">Browse and manage toy selections quickly.</p>
            </div>
        </div>

    </div>
</div>

<!-- Footer -->
<div class="footer">
    <p>Online Toy Store Management System | OOP Project</p>
</div>

</body>
</html>