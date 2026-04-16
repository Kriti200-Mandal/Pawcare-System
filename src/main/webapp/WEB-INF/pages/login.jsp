<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>


 
body {
    height: 100vh;
    background-color: #9fa88f;
}
 
/*container {
     display: flex;
    height: 100vh;
    padding: 30px;
} */

.container {
    display: flex;
   
    gap: 30px;
    padding: 30px;
}

.left,
.right {
    width: 45%;
    border-radius: 14px;
    padding: 50px;
}



.logo img {
    height: 50px;
}


/* .left {
    width: 45%;
    background: #ffffff;
    border-radius: 14px;
    padding: 50px;
    box-shadow: 0 20px 50px rgba(0,0,0,0.15);
    display: flex;
   padding-top: 40px;
    flex-direction: column;
    justify-content: flex-start;
    
} */
.left {
    width: 50%;
    background: white;
    padding: 50px;
    margin: 40px;
    border-radius: 12px;
    box-shadow: 0 15px 40px rgba(0,0,0,0.15);
    
    display: flex;
    flex-direction: column;
    justify-content: center;
}




form input {
    width: 100%;
    padding: 12px;
    margin-bottom: 10px;
    border: none;
    border-bottom: 1px solid #ccc;
}

/* .right {
    width: 50%;
    
    background-size: contain;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
}
 */
 .right {
    width: 45%;
    background-color: #f7f3e8; /* soft paper tone */
    border-radius: 14px;
    padding: 50px;
    box-shadow: 0 20px 50px rgba(0,0,0,0.10);

    display: flex;
    align-items: center;
    justify-content: center;
}


.right-img {
    max-width: 100%;
    max-height: 100%;
    object-fit: contain;
    }


.overlay h2 {
    font-size: 22px;
    margin-bottom: 10px;
}

overlay p {
    font-size: 14px;
    opacity: 0.9;
}

 
. form button {
    width: 100%;
    padding: 12px;
    background: #111;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
 
}

</style>
</head>
<body>
<div class = 'container'>
<div class = 'left'>
<div class = 'logo'>
 <img src="<%= request.getContextPath() %>/images/Paw Pet Care.jpg" alt="pet care">
 <p>   Pet Care</p>
 <div class = 'title'>
<h2>Welcome Back</h2> 
<p> Welcome back! please enter your details</p>
</div>
</div>

<form action="<%= request.getContextPath() %>/LoginController" method = post>
<label for = 'email'>Email:</label><br>
<input type = 'text' name = 'email'><br>
<label for = 'pw'>Password:</label><br>
<input type = 'password' name = 'pw'><br>
<button> Login</button>
</form>
</div>
<div class = 'right'
  
style="background: url('<%= request.getContextPath() %>/images/dog.jpg') 
            no-repeat center;
            background-size: contain;">
            
            
      
     
    </div>
            
   </div>

</body>
</html>