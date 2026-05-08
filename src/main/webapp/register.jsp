<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - ToyLand</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-body">

<div class="auth-card wide">
    <h1>Create Account</h1>
    <p>Join ToyLand as a customer</p>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert error"><%= request.getAttribute("error") %></div>
    <% } %>

    <form action="register" method="post">
        <div class="grid-2">
            <div>
                <label>Full Name</label>
                <input type="text" name="fullName" required>
            </div>

            <div>
                <label>Email</label>
                <input type="email" name="email" required>
            </div>

            <div>
                <label>Username</label>
                <input type="text" name="username" required>
            </div>

            <div>
                <label>Password</label>
                <input type="password" name="password" required>
            </div>

            <div>
                <label>Phone</label>
                <input type="text" name="phone" required>
            </div>

            <div>
                <label>Address</label>
                <input type="text" name="address" required>
            </div>
        </div>

        <button type="submit">Register</button>
    </form>

    <p class="small-text">
        Already have an account? <a href="login.jsp">Login</a>
    </p>
</div>

</body>
</html>