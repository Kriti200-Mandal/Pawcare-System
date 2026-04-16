<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register- Pawcare</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/register.css">
</head>
<body>
  <div class = "container">
  <div class = "left">
  <div class = "logo">
 <!-- - --> <img src="<%= request.getContextPath() %>/images/Paw Pet Care.jpg" alt="pet care">
    Pet Care
  </div>
  <h2>Create account</h2>
  <p class = "subtitle"> Join us and help pet find home </p>
  <p style="color:red;">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</p>
  <form method = 'post' action= './register' novalidate>
  <input type = "text" name = "name" placeholder = "Full name"   pattern="[A-Za-z ]+"
       title="Name should contain only letters">
  <input type = "email" name = "email" placeholder = "Email" >
  <input type = "password" name = "password" placeholder = "Password" >
  <input type = "tel" name = "phone" placeholder = "Phone" 
   
   >
  <button type = "submit">
  Register
  
  </button>
  </form>
  
  <p class = "login-link">
  Already have an account?
  <a href = "LoginController"> login</a>
  </p>
  </div>
  <div class = 'right'
  
style="background: url('<%= request.getContextPath() %>/images/pet.jpg') 
            no-repeat center;
            background-size: contain;">
            
    
  <div class = 'overlay'>
  <h2>
  Adopt, Love care
  </h2>
  <p> Give animals a second chance at life 
  </p>
  </div>
  </div>
  </div>
</body>
</html>