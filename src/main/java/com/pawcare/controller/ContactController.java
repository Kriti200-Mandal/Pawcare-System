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
 * Servlet implementation class contactController
 */
@WebServlet("/contactController")
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 request.getRequestDispatcher("/WEB-INF/pages/contact.jsp")
		               .forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws IOException {

	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String subject = request.getParameter("subject");
	        String message = request.getParameter("message");

	    	try {
		        DbConfig db = new DbConfig();
		        Connection con = db.getConnection();
		        

	            String sql = "INSERT INTO contact_messages(name, email, subject, message) VALUES (?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setString(1, name);
	            ps.setString(2, email);
	            ps.setString(3, subject);
	            ps.setString(4, message);
	            ps.executeUpdate();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    	response.sendRedirect(request.getContextPath() + "/contactController?success=true");


	    }
	}



