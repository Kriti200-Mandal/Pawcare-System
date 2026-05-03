<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Adoption</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pet.css">
</head>
<body>





<nav class="navbar">
    <div class="nav-left">
        <span class="logo">🐾 PawCare</span>
    </div>

    <div class="nav-right">
        <a href="${pageContext.request.contextPath}/user/home">Home</a>
        <a href="${pageContext.request.contextPath}/PetController">Browse Pets</a>
        <a href="#">About</a>
        <a href="${pageContext.request.contextPath}/adoptionController">Adoption</a>
        <a href="${pageContext.request.contextPath}/LogoutController" class="login-btn">Logout</a>
    </div>
</nav>

<div class="pets-container">
<c:if test="${not empty sessionScope.flashMessage}">
    <div class="flash ${sessionScope.flashType}">
        ${sessionScope.flashMessage}
    </div>

    <c:remove var="flashMessage" scope="session"/>
    <c:remove var="flashType" scope="session"/>
</c:if>

    <h2>My Adoption Requests</h2>

    <c:if test="${empty adoptions}">
        <p class="empty-msg">
            You have not made any adoption requests yet.
        </p>
    </c:if>

    <div class="adoption-grid">
        <c:forEach var="a" items="${adoptions}">
            <div class="adoption-card">
            
 <div class="adoption-image">
        <img src="${pageContext.request.contextPath}/images/${a.image}"
             alt="${a.animalName}">
    </div>
            

                <div class="card-header">
                    <h3>${a.animalName}</h3>
                    <span class="status ${a.status}">${a.status}</span>
                </div>

                <div class="card-body">
                    <p><strong>Request ID:</strong> ${a.adoptionId}</p>
                </div>

                <c:if test="${a.status == 'Pending'}">
                    <form method="post"
                          action="${pageContext.request.contextPath}/adoptionController"
                          class="card-action">
                        <input type="hidden" name="action" value="cancel">
                        <input type="hidden" name="adoptionId" value="${a.adoptionId}">
                        <button type="submit" class="cancel-btn">
                            Cancel Request
                        </button>
                    </form>
                </c:if>

            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>