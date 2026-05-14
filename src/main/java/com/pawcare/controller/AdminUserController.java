package com.pawcare.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pawcare.model.ContactMessage;
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
    List<ContactMessage> contactMessages = new ArrayList<>();

    try (Connection con = new DbConfig().getConnection()) {

        //  USERS QUERY
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

        //  CONTACT MESSAGES QUERY
        String contactSql = "SELECT * FROM contact_messages ORDER BY created_at DESC";
        PreparedStatement ps2 = con.prepareStatement(contactSql);
        ResultSet rs2 = ps2.executeQuery();

        while (rs2.next()) {
            ContactMessage c = new ContactMessage();
            c.setName(rs2.getString("name"));
            c.setEmail(rs2.getString("email"));
            c.setSubject(rs2.getString("subject"));
            c.setMessage(rs2.getString("message"));
            c.setCreatedAt(rs2.getString("created_at"));
            contactMessages.add(c);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    //  Send both data to JSP
    request.setAttribute("users", users);
    request.setAttribute("contactMessages", contactMessages);

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
