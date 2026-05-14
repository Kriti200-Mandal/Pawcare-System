<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Contact Messages</h2>

<table border="1" width="100%">
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Subject</th>
        <th>Message</th>
        <th>Date</th>
    </tr>

    <c:forEach var="m" items="${messages}">
        <tr>
            <td>${m.name}</td>
            <td>${m.email}</td>
            <td>${m.subject}</td>
            <td>${m.message}</td>
            <td>${m.createdAt}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>