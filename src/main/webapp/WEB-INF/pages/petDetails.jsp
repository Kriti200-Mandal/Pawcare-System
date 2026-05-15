<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PetDetails</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pet.css">
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pet-details">

    <!-- LEFT IMAGE -->
    <div class="pet-image-large">
        <img src="${pageContext.request.contextPath}/images/${pet.image}"
             alt="${pet.name}">
        <span class="status-badge">${pet.adoptionStatus}</span>
    </div>

    <!-- RIGHT INFO -->
    <div class="pet-info">
        <h1>Meet ${pet.name}</h1>
        <h3>${pet.breed}</h3>

        <div class="info-grid">
    <div><strong>Age:</strong> ${pet.age} years</div>
    <div><strong>Species:</strong> ${pet.species}</div>
    <div><strong>Breed:</strong> ${pet.breed}</div>
</div>

        
        
        <c:if test="${pet.adoptionStatus == 'Available'}">
        <form
        action = "${pageContext.request.contextPath}/adoptionController">
                <input type="hidden" name="animalId" value="${pet.animalId}">
                <button class="adopt-btn">Adopt Me</button>
            </form>
        </c:if>
    </div>

</div>
</body>
</html>