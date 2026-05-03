<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account | PawCare</title>

    <!-- Link external CSS -->
   
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css">
   
    <!-- Font Awesome for icons -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>

<!-- Back link -->
<a href="<%= request.getContextPath() %>/index.html" class="back-link">
    &larr; Back to Home
</a>

<!-- Centered container -->
<div class="page">
    <div class="card">

        <!-- Top icon -->
        <div class="icon-circle">
            <i class="fa-solid fa-heart"></i>
        </div>

        <h1>Create Account</h1>
        <p class="subtitle">Join PawCare today</p>
        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty error}">
    <div style="color:red; margin-bottom:15px; text-align:center;">
        ${error}
    </div>
</c:if>
        

        <!-- Registration form -->
      <!-- - -- <form action="/register" method="post">---->
       <form method = 'post' action= './register' novalidate>

            <div class="field">
                <label>Full Name</label>
                <div class="input-wrapper">
                    <i class="fa-regular fa-user"></i>
                    <input type="text"
                           name="fullName"
                           placeholder="John Doe"
                           required>
                </div>
            </div>

            <div class="field">
                <label>Email Address</label>
                <div class="input-wrapper">
                    <i class="fa-regular fa-envelope"></i>
                    <input type="email"
                           name="email"
                           placeholder="john@example.com"
                           required>
                </div>
            </div>

            <div class="field">
                <label>Password</label>
                <div class="input-wrapper">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password"
                           name="password"
                           placeholder="At least 6 characters"
                           minlength="6"
                           required>
                </div>
            </div>

            <div class="field">
                <label>Confirm Password</label>
                <div class="input-wrapper">
                    <i class="fa-solid fa-lock"></i>
                    <input type="password"
                           name="confirmPassword"
                           placeholder="Re-enter password"
                           minlength="6"
                           required>
                </div>
            </div>

            <button type="submit" class="btn-primary">
                Create Account
            </button>
        </form>

        <!-- Footer -->
        <p class="footer-text">
            Already have an account?
             <a href = "LoginController"> login here</a>
          
        </p>

    </div>
</div>

</body>
</html>