package com.pawcare.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.pawcare.config.DbConfig;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

    String name = request.getParameter("fullName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

     String confirmPassword = request.getParameter("confirmPassword");


     if(name == null || name.trim().isEmpty() ||
       email == null || email.trim().isEmpty() ||
       password == null || password.trim().isEmpty() ||
       confirmPassword == null || confirmPassword.trim().isEmpty())
     {

        request.setAttribute("error", "All fields are required");
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
               .forward(request, response);
        return;
    }
     if(!name.matches("^[A-Za-z ]+$")) {
    	    request.setAttribute("error", "Name must contain only letters");
    	    request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
    	           .forward(request, response);
    	    return;
    	}

   if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
        request.setAttribute("error", "Invalid email format");
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
               .forward(request, response);
        return;
    }

   if(password.length() < 6) {
        request.setAttribute("error", "Password must be at least 6 characters");
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
               .forward(request, response);
        return;
    }
   
  /* phone = phone.trim();

   if(!phone.matches("^[0-9]{10}$")) {
	    request.setAttribute("error", "Phone must be 10 digits and numeric only");
	    request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
	           .forward(request, response);
	    return;
	}*/
   

   String checkEmailSql = "SELECT id FROM users WHERE email = ?";

   String insertSql =
       
		   "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

   try {
       DbConfig db = new DbConfig();
       Connection con = db.getConnection();

       
       PreparedStatement checkPs = con.prepareStatement(checkEmailSql);
       checkPs.setString(1, email);
       ResultSet rs = checkPs.executeQuery();

       if (rs.next()) {
           request.setAttribute("error", "Email already registered. Please login.");
           request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
                  .forward(request, response);
           return;
       }
      

       if (!password.equals(confirmPassword)) {
           request.setAttribute("error", "Passwords do not match");
           request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
                  .forward(request, response);
           return;
       }

       

PreparedStatement ps = con.prepareStatement(insertSql);
ps.setString(1, name);
ps.setString(2, email);
ps.setString(3, password);

ps.setString(4, "USER");


       ps.executeUpdate();

   } catch (Exception e) {
       e.printStackTrace();
       request.setAttribute("error", e.getMessage());
       request.getRequestDispatcher("/WEB-INF/pages/register.jsp")
              .forward(request, response);
       return;
   }
  

request.getSession().setAttribute(
    "success",
    "Registration successful. Please login."
);



response.sendRedirect(request.getContextPath() + "/LoginController");

		
	}

}
