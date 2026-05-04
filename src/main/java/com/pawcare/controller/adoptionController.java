package com.pawcare.controller;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.pawcare.config.DbConfig;
import com.pawcare.model.Adoption;

@WebServlet("/adoptionController")
public class adoptionController extends HttpServlet {

    //  READ – show adoption page
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Adoption> adoptions = new ArrayList<>();

        try {
            Integer userId = (Integer) request.getSession().getAttribute("userId");

            // Not logged in → redirect
            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/LoginController");
                return;
            }

            Connection con = new DbConfig().getConnection();

            String sql = """
                
              SELECT ad.adoption_id,
                ad.animal_id,
           an.name,
          an.image,
           ad.status
       FROM adoptions ad
    JOIN animals an ON ad.animal_id = an.animal_id
     WHERE ad.user_id = ?

            """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Adoption a = new Adoption();
                a.setAdoptionId(rs.getInt("adoption_id"));
                a.setAnimalId(rs.getInt("animal_id"));
                a.setAnimalName(rs.getString("name"));
                a.setStatus(rs.getString("status"));
                a.setUserId(userId);
                a.setImage(rs.getString("image"));

                adoptions.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

       
        request.setAttribute("adoptions", adoptions);
        request.getRequestDispatcher("/WEB-INF/pages/adoption.jsp")
               .forward(request, response);
    }

    //  CREATE / DELETE
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            Connection con = new DbConfig().getConnection();

            if ("adopt".equals(action)) {
                Integer userId = (Integer) request.getSession().getAttribute("userId");
                int animalId = Integer.parseInt(request.getParameter("animalId"));

                if (userId == null) {
                    response.sendRedirect(request.getContextPath() + "/LoginController");
                    return;
                }

                String sql = "INSERT INTO adoptions (user_id, animal_id) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, animalId);

                int rows = ps.executeUpdate();
                System.out.println("Rows inserted into adoptions: " + rows);
            }

            if ("cancel".equals(action)) {
                int adoptionId = Integer.parseInt(request.getParameter("adoptionId"));

                String sql = "DELETE FROM adoptions WHERE adoption_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, adoptionId);
                ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute(
        	    "flashMessage",
        	    "Adoption request sent successfully 🐾"
        	);
        	request.getSession().setAttribute("flashType", "success");

        response.sendRedirect(request.getContextPath() + "/adoptionController");
    }
}
