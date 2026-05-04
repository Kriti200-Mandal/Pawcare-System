package com.pawcare.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;


import com.pawcare.config.DbConfig;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/user/home")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }

        try (Connection con = new DbConfig().getConnection()) {

        	 request.setAttribute("availablePets",
        	                getCount(con,
        	                    "SELECT COUNT(*) FROM animals WHERE adoption_status='Available'"
        	                )
        	            );

        	 request.setAttribute("myRequests",
        	                getCount(con,
        	                    "SELECT COUNT(*) FROM adoptions WHERE user_id=?",
        	                    userId
        	                )
        	            );

        	 request.setAttribute("adoptedPets",
        	                getCount(con,
        	                    "SELECT COUNT(*) FROM adoptions WHERE user_id=? AND status='Approved'",
        	                    userId
        	                )
        	            );}catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/userHome.jsp")
               .forward(request, response);
    }



	 private int getCount(Connection con, String sql) throws Exception {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1);
	    }

	 private int getCount(Connection con, String sql, int userId) throws Exception {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1);
	    }
	 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
