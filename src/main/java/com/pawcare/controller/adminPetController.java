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
       String action = request.getParameter("action");

       if ("edit".equals(action)) {
         int animalId = Integer.parseInt(request.getParameter("animalId"));

 try (Connection con = new DbConfig().getConnection()) {

         PreparedStatement ps = con.prepareStatement(
             "SELECT * FROM animals WHERE animal_id=?"
         );
         ps.setInt(1, animalId);
         ResultSet rs = ps.executeQuery();

         if (rs.next()) {

        	 String status = rs.getString("adoption_status");

        	            //  BLOCK EDIT IF NOT AVAILABLE
        	            if (!"Available".equals(status)) {
        	                request.getSession().setAttribute(
        	                    "flashMessage", "Adopted pets cannot be edited ❌"
        	                );
        	                request.getSession().setAttribute("flashType", "error");
        	                response.sendRedirect(request.getContextPath() + "/admin/pets");
        	                return;
        	            }

             Animal pet = new Animal();
             pet.setAnimalId(rs.getInt("animal_id"));
             pet.setName(rs.getString("name"));
             pet.setSpecies(rs.getString("species"));
             pet.setBreed(rs.getString("breed"));
             pet.setAge(rs.getInt("age"));
             pet.setImage(rs.getString("image"));

             request.setAttribute("editPet", pet);
         }

     } catch (Exception e) {
         e.printStackTrace();
     }
       }

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
		if (!"admin".equals(request.getSession().getAttribute("userType"))) {

		    request.getSession().setAttribute(
		        "flashMessage",
		        "Access denied ❌ Only authorized admin users can access this page."
		    );
		    request.getSession().setAttribute("flashType", "error");

		    response.sendRedirect(request.getContextPath() + "/user/home");
		    return;
		}
		 String action = request.getParameter("action");

		        try (Connection con = new DbConfig().getConnection()) {

		            //  ADD PET
		            if ("add".equals(action)) {
		            	String name = request.getParameter("name");

             if (!name.matches("[a-zA-Z ]+")) {
                   request.getSession().setAttribute(
            "flashMessage", "Pet name must contain only alphabets ❌"
                   );
              request.getSession().setAttribute("flashType", "error");
              response.sendRedirect(request.getContextPath() + "/admin/pets");
             return;
         }

             int age;

                try {
                    age = Integer.parseInt(request.getParameter("age"));

                    if (age < 0) {
                        request.getSession().setAttribute(
                            "flashMessage", "Age cannot be negative ❌"
                        );
                        request.getSession().setAttribute("flashType", "error");
                        response.sendRedirect(request.getContextPath() + "/admin/pets");
                        return;
                    }
                } catch (NumberFormatException e) {
                    request.getSession().setAttribute(
                        "flashMessage", "Invalid age entered ❌"
                    );
                    request.getSession().setAttribute("flashType", "error");
                    response.sendRedirect(request.getContextPath() + "/admin/pets");
                    return;
                }



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
                      // update
                      if ("update".equals(action)) {

                    	    int animalId = Integer.parseInt(request.getParameter("animalId"));

                    	    //  CHECK STATUS FIRST
                    	    PreparedStatement checkPs =
                    	        con.prepareStatement("SELECT adoption_status FROM animals WHERE animal_id=?");
                    	    checkPs.setInt(1, animalId);
                    	    ResultSet rs = checkPs.executeQuery();

                    	    if (rs.next() && !"Available".equals(rs.getString("adoption_status"))) {
                    	        request.getSession().setAttribute(
                    	            "flashMessage", "Cannot update an adopted pet ❌"
                    	        );
                    	        request.getSession().setAttribute("flashType", "error");
                    	        response.sendRedirect(request.getContextPath() + "/admin/pets");
                    	        return;
                    	    }

                    	    //  PERFORM UPDATE
                    	    PreparedStatement ps = con.prepareStatement("""
                    	        UPDATE animals
                    	        SET name=?, species=?, breed=?, age=?, image=?
                    	        WHERE animal_id=?
                    	    """);

                    	    ps.setString(1, request.getParameter("name"));
                    	    ps.setString(2, request.getParameter("species"));
                    	    ps.setString(3, request.getParameter("breed"));
                    	    ps.setInt(4, Integer.parseInt(request.getParameter("age")));
                    	    ps.setString(5, request.getParameter("image"));
                    	    ps.setInt(6, animalId);

                    	    ps.executeUpdate();

                    	    request.getSession().setAttribute(
                    	        "flashMessage", "Pet updated successfully ✏️"
                    	    );
                    	    request.getSession().setAttribute("flashType", "success");
                    	}
		        } catch (Exception e) {
                        e.printStackTrace();
                    }

                    response.sendRedirect(request.getContextPath() + "/admin/pets");
                }


		

}
