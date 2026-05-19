


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - ToyLand</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="auth-body">

<div class="auth-card">
    <h1>Login</h1>
    <p>Welcome back to ToyLand</p>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert error"><%= request.getAttribute("error") %></div>
    <% } %>

    <% if ("registered".equals(request.getParameter("success"))) { %>
        <div class="alert success">Registration successful. Please login.</div>
    <% } %>

    <% if ("logout".equals(request.getParameter("msg"))) { %>
        <div class="alert success">Logout successful.</div>
    <% } %>

    <form action="login" method="post">
       <label>Username</label>
       <input type="text"
              name="username"
              pattern="[A-Za-z]+"
              title="Username should contain letters only"
              oninput="this.value = this.value.replace(/[^A-Za-z]/g, '')"
              required>

        <label>Password</label>
        <input type="password" name="password" required>

        <button type="submit">Login</button>
    </form>

    <p class="small-text">
        New customer? <a href="register.jsp">Create Account</a>
    </p>


</div>

</body>
</html>