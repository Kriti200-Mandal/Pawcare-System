<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@  taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%> 
   <%@ page import="jakarta.servlet.http.HttpSession" %>
   <%@ page import="jakarta.servlet.http.HttpServletRequest" %>
   

<!DOCTYPE html>
<html>
<head>
    <title>Browse Pets | PawCare</title>
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
   
 <h1 class="page-title">Find Your Perfect Companion</h1>
    
<p class="subtitle">
    ${animalCount} animals waiting for their forever homes
</p>
    
    <div class="pet-grid">
        <c:forEach var="a" items="${animals}">
          <div class="pet-card">

    <div class="pet-image">
        <c:choose>
            <c:when test="${not empty a.image}">
                <img src="${pageContext.request.contextPath}/images/${a.image}" alt="${a.name}">
            </c:when>
            <c:otherwise>
                <img src="${pageContext.request.contextPath}/images/pet.jpg" alt="Pet">
            </c:otherwise>
        </c:choose>

        <span class="status-badge">${a.adoptionStatus}</span>
    </div>

    <div class="pet-info">
        <h3>${a.name}</h3>
        <p class="breed">${a.breed}</p>

        <div class="tags">
            <span>${a.age} yrs</span>
            <span>${a.species}</span>
        </div>
        <form action="${pageContext.request.contextPath}/adoptionController" method="post">
    <input type="hidden" name="action" value="adopt">
    <input type="hidden" name="animalId" value="${a.animalId}">
    <button class="adopt-btn">Adopt</button>
</form>

        
    </div>

</div>
        </c:forEach>
    </div>
   
</div>

</body>
</html>
   