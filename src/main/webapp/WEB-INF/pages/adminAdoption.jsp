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
<!-- - --
<div class="navbar">
    <span class="logo">🐾 PawCare Admin</span>
    <a href="${pageContext.request.contextPath}/admin/home">Dashboard</a>
    <a href="${pageContext.request.contextPath}/admin/adoptions" class="active">
        Adoption Requests
    </a>
    <a href="${pageContext.request.contextPath}/LogoutController">Logout</a>
</div>-->

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
        
<div class="pet-card">

            <!-- IMAGE -->
            <div class="pet-image">
                <img src="${pageContext.request.contextPath}/images/${a.image}"
                     alt="${a.animalName}">
                
                <span class="status-badge ${a.status}">
                    ${a.status}
                </span>
            </div>

            <!-- INFO -->
        
<div class="pet-info">
                <h3>${a.animalName}</h3>

                <p class="breed">
                    User: ${a.userName}
                </p>
        
 <!-- ACTIONS -->
                <c:if test="${a.status == 'Pending'}">
                    <form method="post"
                          action="${pageContext.request.contextPath}/admin/adoptions">
                        <input type="hidden" name="adoptionId"
                               value="${a.adoptionId}">
                        <button name="action" value="approve"
                                class="adopt-btn">
                            Approve
                        </button>
        
<button name="action" value="reject"
                                class="adopt-btn"
                                style="background:#dc2626;">
                            Reject
                        </button>
                    </form>
                </c:if>
            </div>
        
 </div>
        
           <!-- -- -- <div class="adoption-card">

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

            </div>---->
        </c:forEach>
    </div>
</div>

</body>
</html>