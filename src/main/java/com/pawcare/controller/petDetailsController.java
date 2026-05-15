package com.pawcare.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.pawcare.model.Animal;
import java.sql.*;
import java.util.*;
import com.pawcare.config.DbConfig;

/**
 * Servlet implementation class petDetailsController
 */
@WebServlet("/pet/details")
public class petDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public petDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("animalId");

        // ✅ SAFETY CHECK
        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/PetController");
            return;
        }

        int animalId = Integer.parseInt(idParam);

        Animal pet = null;

        try (Connection con = new DbConfig().getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM animals WHERE animal_id=?"
            );
            ps.setInt(1, animalId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pet = new Animal();
                pet.setAnimalId(rs.getInt("animal_id"));
                pet.setName(rs.getString("name"));
                pet.setSpecies(rs.getString("species"));
                pet.setBreed(rs.getString("breed"));
                pet.setAge(rs.getInt("age"));
                pet.setImage(rs.getString("image"));
                pet.setAdoptionStatus(rs.getString("adoption_status"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pet == null) {
            response.sendRedirect(request.getContextPath() + "/PetController");
            return;
        }

        request.setAttribute("pet", pet);
        request.getRequestDispatcher("/WEB-INF/pages/petDetails.jsp")
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
