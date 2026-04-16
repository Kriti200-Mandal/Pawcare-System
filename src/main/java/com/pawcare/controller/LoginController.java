package com.pawcare.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import com.pawcare.config.DbConfig;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 String email = request.getParameter("email");
		    String password = request.getParameter("pw");
		    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

		    if ("admin@gmail.com".equals(email) && "admin123".equals(password)) {

		           HttpSession session = request.getSession();
		           session.setAttribute("userType", "admin");

		           response.sendRedirect(request.getContextPath() + "/admin/home");
		           return; 
		       }


try {
        DbConfig db = new DbConfig();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
                   // login success

            HttpSession session = request.getSession();
            session.setAttribute("userType", "user");
            session.setAttribute("userEmail", email);

            response.sendRedirect(request.getContextPath() + "/user/home");

                  
               } else {
                   // login failed
                   request.setAttribute("error", "Invalid email or password");
                   request.getRequestDispatcher("/WEB-INF/pages/login.jsp")
                          .forward(request, response);
               }

           } catch (Exception e) {
               e.printStackTrace();
           }



		
	}

}
