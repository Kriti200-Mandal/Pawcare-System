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
 * Servlet implementation class ForgotPasswordController
 */
@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

request.getRequestDispatcher("/WEB-INF/pages/forgotPassword.jsp")  .forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
 try {
        DbConfig db = new DbConfig();
        Connection con = db.getConnection();
        

String checkSql = "SELECT id FROM users WHERE email = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, email);
            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                request.setAttribute("error", "Email not found");
                request.getRequestDispatcher("/WEB-INF/pages/forgotPassword.jsp")
                       .forward(request, response);
                return;
            }

String updateSql = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement updatePs = con.prepareStatement(updateSql);
            updatePs.setString(1, newPassword); // (hash here if using encryption)
            updatePs.setString(2, email);
            updatePs.executeUpdate();

            response.sendRedirect(request.getContextPath() + "/LoginController?reset=success");

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/LoginController");
                }


	}

}
