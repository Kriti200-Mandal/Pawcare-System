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
import com.pawcare.model.Adoption;


/**
 * Servlet implementation class AdminAdoptionController
 */
@WebServlet("/admin/adoptions")
public class AdminAdoptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAdoptionController() {
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

		        List<Adoption> list = new ArrayList<>();


try (Connection con = new DbConfig().getConnection()) {

            String sql = """
                SELECT ad.adoption_id, u.name AS user_name,
                       an.name AS animal_name, an.image, ad.status
                FROM adoptions ad
                JOIN users u ON ad.user_id = u.id
                JOIN animals an ON ad.animal_id = an.animal_id
""";
PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                           Adoption a = new Adoption();
                           a.setAdoptionId(rs.getInt("adoption_id"));
                           a.setUserName(rs.getString("user_name"));
                           a.setAnimalName(rs.getString("animal_name"));
                           a.setImage(rs.getString("image"));
                           a.setStatus(rs.getString("status"));
                           list.add(a);
                       }

                   } catch (Exception e) {
                       e.printStackTrace();
                   }

		        request.setAttribute("adoptions", list);
		               request.getRequestDispatcher("/WEB-INF/pages/adminAdoption.jsp")
		                  .forward(request, response);
		           }


/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

String action = request.getParameter("action");
        int adoptionId = Integer.parseInt(request.getParameter("adoptionId"));

        try (Connection con = new DbConfig().getConnection()) {

            String sql = "UPDATE adoptions SET status=? WHERE adoption_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, action.equals("approve") ? "Approved" : "Rejected");
            ps.setInt(2, adoptionId);
            ps.executeUpdate();

        }

catch (Exception e) {
            e.printStackTrace();
        }
        String msg;

        if ("approve".equals(action)) {
            msg = "Adoption request approved successfully ✅";
        } else {
            msg = "Adoption request rejected ❌";
        }

        request.getSession().setAttribute("flashMessage", msg);
        request.getSession().setAttribute("flashType", "success");

        response.sendRedirect(request.getContextPath() + "/admin/adoptions");
    }

}


