<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@  taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%> 
   <%@ page import="jakarta.servlet.http.HttpSession" %>
   <%@ page import="jakarta.servlet.http.HttpServletRequest" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


    <title>Pets | PawCare</title>

    <!-- CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f5f5f5;
        }
        .status-available {
            color: green;
            font-weight: bold;
        }
        .status-adopted {
            color: red;
            font-weight: bold;
        }
    </style>
</head>

<body>

<h2>🐾 Pets Management</h2>

<!-- No animals case -->
<c:if test="${empty animals}">
    <p>No animals available.</p>
</c:if>

<!-- Animals table -->
<c:if test="${not empty animals}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Species</th>
            <th>Breed</th>
            <th>Age</th>
            <th>Status</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="a" items="${animals}">
            <tr>
                <td>${a.animalId}</td>
                <td>${a.name}</td>
                <td>${a.species}</td>
                <td>${a.breed}</td>
                <td>${a.age}</td>

                <td>
                    <c:choose>
                        <c:when test="${a.adoptionStatus == 'Available'}">
                            <span class="status-available">Available</span>
                        </c:when>
                        <c:otherwise>
                            <span class="status-adopted">Adopted</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
