<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="pets-container" style="text-align:center; margin-top:80px;">
    <h1>404 🐾</h1>
    <h2>Oops! Page not found</h2>
    <p>The page you’re looking for doesn’t exist or you may have logged out.</p>

    <div style="margin-top:30px;">
    <a>
        ${pageContext.request.contextPath}/user/home
            Go to Home
        </a>

     <a>   ${pageContext.request.contextPath}/PetController
            Browse Pets
        </a>

     <a>   ${pageContext.request.contextPath}/LoginController

Login Again
        </a>
    </div>
</div>

</body>
</html>