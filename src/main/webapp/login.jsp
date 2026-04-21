<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Toy Store</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap + Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">

    <style>
        body {
            background: linear-gradient(135deg, #ffe4ec, #e0f7ff, #fff7d6);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .login-card {
            background: #fff;
            border-radius: 20px;
            padding: 35px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            animation: fadeIn 0.6s ease;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(20px);}
            to {opacity: 1; transform: translateY(0);}
        }

        .title {
            text-align: center;
            font-weight: bold;
            color: #ff6b6b;
        }

        .subtitle {
            text-align: center;
            font-size: 14px;
            color: #777;
            margin-bottom: 20px;
        }

        .logo {
            display: block;
            margin: 0 auto 15px auto;
            width: 80px;
        }

        .form-control {
            border-radius: 10px;
        }

        .input-group-text {
            border-radius: 10px 0 0 10px;
        }

        .btn-login {
            background: linear-gradient(90deg, #00b894, #55efc4);
            border: none;
            border-radius: 10px;
            font-weight: bold;
            padding: 10px;
            transition: 0.3s;
        }

        .btn-login:hover {
            opacity: 0.9;
        }

        .extra-links {
            font-size: 14px;
        }

        .extra-links a {
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="login-card">

    <!-- Logo -->
    <img src="https://cdn-icons-png.flaticon.com/512/3081/3081559.png" class="logo">

    <h3 class="title">Welcome Back 👋</h3>
    <p class="subtitle">Login to access your Toy Store dashboard</p>

    <!-- Error Message -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <div class="alert alert-danger text-center"><%= message %></div>
    <% } %>

    <form action="login" method="post">

        <div class="mb-3">
            <label class="form-label">Username</label>
            <div class="input-group">
                <span class="input-group-text"><i class="bi bi-person-fill"></i></span>
                <input type="text" name="username" class="form-control" placeholder="Enter username" required>
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label">Password</label>
            <div class="input-group">
                <span class="input-group-text"><i class="bi bi-lock-fill"></i></span>
                <input type="password" name="password" class="form-control" placeholder="Enter password" required>
            </div>
        </div>

        <button type="submit" class="btn btn-login w-100 mt-2">
            <i class="bi bi-box-arrow-in-right"></i> Login
        </button>

        <div class="text-center mt-3 extra-links">
            <p>
                Don’t have an account?
                <a href="register.jsp" class="text-primary">Register</a>
            </p>
            <a href="index.jsp">
                <i class="bi bi-arrow-left"></i> Back to Home
            </a>
        </div>

    </form>
</div>

</body>
</html>