<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin | Adoption Requests</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/pet.css">
</head>
<body>




<!-- ADMIN NAVBAR -->
<div class="navbar">
    <span class="logo">🐾 PawCare Admin</span>
    <a href="${pageContext.request.contextPath}/admin/home">Dashboard</a>
    <a href="${pageContext.request.contextPath}/admin/adoptions" class="active">
        Adoption Requests
    </a>
    <a href="${pageContext.request.contextPath}/LogoutController">Logout</a>
</div>

<div class="pets-container">
<c:if test="${not empty sessionScope.flashMessage}">
    <div class="flash ${sessionScope.flashType}">
        ${sessionScope.flashMessage}
    </div>

    <c:remove var="flashMessage" scope="session"/>
    <c:remove var="flashType" scope="session"/>
</c:if>
    <h2>All Adoption Requests</h2>

   
    <div class="adoption-grid">
        <c:forEach var="a" items="${adoptions}">
            <div class="adoption-card">

                <img src="${pageContext.request.contextPath}/images/${a.image}">

                <h3>${a.animalName}</h3>
                <p>User: ${a.userName}</p>
                <p>Status: ${a.status}</p>

                <c:if test="${a.status == 'Pending'}">
                    <form method="post"
                          action="${pageContext.request.contextPath}/admin/adoptions">
                        <input type="hidden" name="adoptionId"
                               value="${a.adoptionId}">
                        <button name="action" value="approve">Approve</button>
                        <button name="action" value="reject">Reject</button>
                    </form>
                </c:if>

            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>