package com.pawcare.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


import com.pawcare.config.DbConfig;
/**
 * Servlet implementation class homeController
 */
@WebServlet("/admin/home")
public class homeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public homeController() {
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

        try  {
        	
        	 Connection con = new DbConfig().getConnection();

            request.setAttribute("totalUsers",
                getCount(con, "SELECT COUNT(*) FROM users"));

            request.setAttribute("totalPets",
                getCount(con, "SELECT COUNT(*) FROM animals"));

request.setAttribute("totalAdoptions",
                getCount(con, "SELECT COUNT(*) FROM adoptions"));

            request.setAttribute("pendingAdoptions",
                getCount(con,
                    "SELECT COUNT(*) FROM adoptions WHERE status='Pending'"));

        } catch (Exception e) {
                   e.printStackTrace();
               }



		request.getRequestDispatcher("/WEB-INF/pages/adminHome.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 private int getCount(Connection con, String sql) throws Exception {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1);
	    }


}
