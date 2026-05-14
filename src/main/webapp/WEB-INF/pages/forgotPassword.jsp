<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password | PawCare</title>

<style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background: #fff7ed;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .reset-wrapper {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .reset-card {
        background: white;
        width: 380px;
        padding: 35px 30px;
        border-radius: 22px;
        box-shadow: 0 15px 40px rgba(0,0,0,0.12);
        text-align: center;
    }

    /*  Heart sticker */
    .sticker {
        width: 70px;
        height: 70px;
        background: #ffe0bd;
        border-radius: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0 auto 20px;
        font-size: 30px;
    }

    .reset-card h2 {
        margin: 0;
        font-size: 26px;
    }

    .subtitle {
        font-size: 14px;
        color: #777;
        margin: 8px 0 25px;
    }

    .reset-card input {
        width: 100%;
        padding: 12px 14px;
        margin-bottom: 16px;
        border-radius: 12px;
        border: none;
        background: #f3f3f3;
        font-size: 14px;
        outline: none;
    }

    .reset-card input:focus {
        background: #ededed;
    }

    .reset-card button {
        width: 100%;
        padding: 12px;
        border: none;
        border-radius: 30px;
        background: linear-gradient(135deg, #000, #333);
        color: white;
        font-size: 15px;
        font-weight: 600;
        cursor: pointer;
        margin-top: 10px;
    }

    .reset-card button:hover {
        opacity: 0.9;
    }

    .error {
        color: #d00000;
        font-size: 14px;
        margin-bottom: 12px;
    }

    .reset-card a {
        display: block;
        margin-top: 18px;
        font-size: 14px;
        color: #ff7a00;
        text-decoration: none;
        font-weight: 500;
    }

    .reset-card a:hover {
        text-decoration: underline;
    }
</style>

</head>
<body>

<div class="reset-wrapper">
    <div class="reset-card">

        <div class="sticker">❤️</div>

        <h2>Reset Password</h2>
        <div class="subtitle">Enter your email and create a new password</div>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/ForgotPasswordController">
            <input type="email" name="email" placeholder="Email Address" required>
            <input type="password" name="newPassword" placeholder="New Password" required>

            <button type="submit">Reset Password</button>
        </form>

        <a href="${pageContext.request.contextPath}/LoginController">Back to Login</a>
    </div>
</div>

</body>
</html>