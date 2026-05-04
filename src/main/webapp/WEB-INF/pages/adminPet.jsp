<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    <h2>Manage Pets</h2>

    <!--  Flash message -->
    <c:if test="${not empty sessionScope.flashMessage}">
        <div class="flash-wrapper">
            <div class="flash ${sessionScope.flashType}">
                ${sessionScope.flashMessage}
            </div>
        </div>
        <c:remove var="flashMessage" scope="session"/>
        <c:remove var="flashType" scope="session"/>
    </c:if>
   
 <form method="post" class="add-pet-form">
        <input type="hidden" name="action" value="add">

        <input name="name" placeholder="Pet Name" required>
        <input name="species" placeholder="Species" required>
        <input name="breed" placeholder="Breed" required>
        <input name="age" type="number" placeholder="Age" required>
        <input name="image" placeholder="Image file (dog.jpg)" required>
         
 <button>Add Pet</button>
    </form>

    <!--  PET LIST -->
    <div class="pet-grid">
        <c:forEach var="p" items="${pets}">
           <div class="pet-card">

    <div class="pet-image">
        <img src="${pageContext.request.contextPath}/images/${p.image}" alt="${p.name}">
    </div>

    <h3>${p.name}</h3>
    <p>${p.species} • ${p.breed}</p>
    <p>Age: ${p.age}</p>

    <form method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="animalId" value="${p.animalId}">
        <button class="delete-btn">Delete</button>
    </form>

</div>
        </c:forEach>
    </div>
         </div>


</body>
</html>