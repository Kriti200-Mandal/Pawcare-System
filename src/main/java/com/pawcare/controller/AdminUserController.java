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
import com.pawcare.model.AdminViewUser;
/**
 * Servlet implementation class AdminUserController
 */
@WebServlet("/admin/users")
public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 if (!"admin".equals(request.getSession().getAttribute("userType"))) {
		            response.sendRedirect(request.getContextPath() + "/LoginController");
		            return;
		        }

		        List<AdminViewUser> users = new ArrayList<>();

		        try (Connection con = new DbConfig().getConnection()) {

		            String sql = """
		            		
SELECT u.id, u.name AS user_name, u.email,
                       an.name AS pet_name, ad.status
                FROM users u
                LEFT JOIN adoptions ad ON u.id = ad.user_id
                LEFT JOIN animals an ON ad.animal_id = an.animal_id
                WHERE u.role = 'USER'
            """;

PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                AdminViewUser u = new AdminViewUser();
                u.setUserId(rs.getInt("id"));
                u.setUserName(rs.getString("user_name"));
                u.setEmail(rs.getString("email"));
                u.setPetName(rs.getString("pet_name"));
                u.setAdoptionStatus(rs.getString("status"));
                users.add(u);
            }

        } catch (Exception e) {

        	   e.printStackTrace();
        	        }

        	        request.setAttribute("users", users);
        	        request.getRequestDispatcher("/WEB-INF/pages/UserView.jsp")

        	        .forward(request, response);
        	        }

		            		

		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
