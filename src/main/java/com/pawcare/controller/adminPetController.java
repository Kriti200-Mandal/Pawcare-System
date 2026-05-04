package com.pawcare.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pawcare.model.Adoption;
import java.sql.*;
import java.util.*;

import com.pawcare.config.DbConfig;
import com.pawcare.model.Animal;
/**
 * Servlet implementation class adminPetController
 */
@WebServlet("/admin/pets")
public class adminPetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminPetController() {
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


       List<Animal> pets = new ArrayList<>();

		        try (Connection con = new DbConfig().getConnection()) {

		            String sql = "SELECT * FROM animals";

		            PreparedStatement ps = con.prepareStatement(sql);
		                       ResultSet rs = ps.executeQuery();

		                       while (rs.next()) {
		                           Animal a = new Animal();
		                           a.setAnimalId(rs.getInt("animal_id"));
		                           a.setName(rs.getString("name"));
		                           a.setSpecies(rs.getString("species"));
		                           a.setBreed(rs.getString("breed"));
		                           a.setAge(rs.getInt("age"));
		                           a.setImage(rs.getString("image"));

		                           a.setAdoptionStatus(rs.getString("adoption_status"));
		                                          pets.add(a);
		                                      }

		                                  } catch (Exception e) {
		                                      e.printStackTrace();
		                                  }

		        request.setAttribute("pets", pets);
		               request.getRequestDispatcher("/WEB-INF/pages/adminPet.jsp")
		                      .forward(request, response);
		           }


	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 String action = request.getParameter("action");

		        try (Connection con = new DbConfig().getConnection()) {

		            // ✅ ADD PET
		            if ("add".equals(action)) {
		                String sql = """
		                    INSERT INTO animals (name, species, breed, age, image, adoption_status)
		                    VALUES (?, ?, ?, ?, ?, 'Available')
		                """;

		                PreparedStatement ps = con.prepareStatement(sql);
		                               ps.setString(1, request.getParameter("name"));
		                               ps.setString(2, request.getParameter("species"));
		                               ps.setString(3, request.getParameter("breed"));
		                               ps.setInt(4, Integer.parseInt(request.getParameter("age")));
		                               ps.setString(5, request.getParameter("image"));
		                               ps.executeUpdate();

		                               request.getSession().setAttribute(
		                                                  "flashMessage", "Pet added successfully 🐾");
		                                              request.getSession().setAttribute("flashType", "success");
		                                          }

                      if ("delete".equals(action)) {
                 int id = Integer.parseInt(request.getParameter("animalId"));
                   PreparedStatement ps =
                    con.prepareStatement("DELETE FROM animals WHERE animal_id=?");
                   ps.setInt(1, id);
                   ps.executeUpdate();

                request.getSession().setAttribute(
                    "flashMessage", "Pet deleted successfully ❌");

                request.getSession().setAttribute("flashType", "error");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    response.sendRedirect(request.getContextPath() + "/admin/pets");
                }



		

}
