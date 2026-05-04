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
        <a href="${pageContext.request.contextPath}/user/home">Home</a>
        <a href="${pageContext.request.contextPath}/PetController" >Browse Pets</a>
        <a href="#">About</a>
        <a href="#">Contact</a>
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
</div>

</body>
</html>