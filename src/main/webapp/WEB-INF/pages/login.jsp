<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login | PawCare</title>

    <!-- External CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css">

    <!-- Font Awesome Icons -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>

<a href="<%= request.getContextPath() %>/index.html" class="back-link">
     Back to Home
</a>

<div class="page">
    <div class="card">

        <!-- Icon -->
        <div class="icon-circle">
            <i class="fa-solid fa-heart"></i>
        </div>

        <h1>Welcome Back</h1>
        <p class="subtitle">Login to PawCare</p>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty error}">
    <div style="color:red; text-align:center; margin-bottom:15px;">
        ${error}
    </div>
</c:if>
        

        <!-- Login Form -->
        <form action="<%= request.getContextPath() %>/LoginController" method="post">

            <div class="field">
                <label>Email Address</label>
                <div class="input-wrapper">
                    <i class="fa-regular fa-envelope"></i>
                    <input type="email"
                           name="email"
                           placeholder="Enter your email"
                           required>
                </div>
            </div>

            <div class="field">
                <label>Password</label>
                <div class="input-wrapper">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password"
                           name="password"
                           placeholder="Enter your password"
                           required>
                </div>
            </div>

            <button type="submit" class="btn-primary">
                Login
            </button>
        </form>

        <!-- Footer -->
        <p class="footer-text">
            Don't have an account?
            <a href= "RegisterController">
             
                Register here
            </a>
        </p>

    </div>
</div>

</body>
</html>