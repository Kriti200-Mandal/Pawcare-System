<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User View</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pet.css">
</head>
<body>
<nav class="navbar">
    <div class="nav-left">
        <span class="logo">🐾 PawCare</span>
    </div>

    <div class="nav-right">
         <a href="${pageContext.request.contextPath}/admin/home">Home</a>
        <a href="${pageContext.request.contextPath}/admin/pets" >Pets</a>
        <a href="${pageContext.request.contextPath}/aboutController">About</a>
       
        <a href="${pageContext.request.contextPath}/admin/adoptions">Adoption</a>
        <a href="${pageContext.request.contextPath}/LogoutController" class="login-btn">Logout</a>
    </div>
</nav>

<div class="pets-container">
    <h2>Manage Users</h2>

    <table class="adoption-table">
        <tr>
            <th>User</th>
            <th>Email</th>
            <th>Adopted Pet</th>
            <th>Status</th>
        </tr>

 <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.userName}</td>
                <td>${u.email}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty u.petName}">
                            ${u.petName}
                        </c:when>
                        <c:otherwise>
                            —
                        </c:otherwise>

   </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${u.adoptionStatus == 'Approved'}">
                            <span class="status Approved">Approved</span>
                        </c:when>
                        <c:when test="${u.adoptionStatus == 'Pending'}">
                            <span class="status Pending">Pending</span>
                        </c:when>
                        <c:otherwise>

        —
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>

<hr style="margin:50px 0;">
<h2 style="margin-top:40px;">Contact Messages</h2>

<table class="adoption-table">
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Subject</th>
        <th>Message</th>
        <th>Date</th>
    </tr>

    <c:forEach var="c" items="${contactMessages}">
        <tr>
            <td>${c.name}</td>
            <td>${c.email}</td>
            <td>${c.subject}</td>
            <td>${c.message}</td>
            <td>${c.createdAt}</td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>