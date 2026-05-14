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
 * Servlet implementation class aboutController
 */
@WebServlet("/aboutController")
public class aboutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aboutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		try {
	        DbConfig db = new DbConfig();
	        Connection con = db.getConnection();
	        

		            request.setAttribute("animalsRescued",
		                getCount(con, "SELECT COUNT(*) FROM animals"));

		            request.setAttribute("successfulAdoptions",
		                getCount(con, "SELECT COUNT(*) FROM adoptions WHERE status='Approved'"));

		            request.setAttribute("volunteers", 50); 
		            request.setAttribute("partners", 15);

		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		        request.getRequestDispatcher("/WEB-INF/pages/about.jsp")
		               .forward(request, response);
		    }

		    private int getCount(Connection con, String sql) throws Exception {
		        PreparedStatement ps = con.prepareStatement(sql);
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
