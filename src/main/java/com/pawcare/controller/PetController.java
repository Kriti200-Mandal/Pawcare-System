package com.pawcare.controller;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pawcare.dao.AnimalDAO;
import com.pawcare.model.Animal;
import java.util.List;
import java.util.*;

/**
 * Servlet implementation class PetController
 */
@WebServlet("/PetController")
public class PetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetController() {
        super();
        // TODO Auto-generated constructor stub
    }
    private List<Animal> linearSearchByType(List<Animal> animals, String keyword) {

        List<Animal> result = new ArrayList<>();

        for (Animal a : animals) { // ✅ linear traversal
            if (a.getSpecies().equalsIgnoreCase(keyword)) {
                result.add(a);
            }
        }
        return result;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
AnimalDAO dao = new AnimalDAO();
        List<Animal> animals = dao.getAllAnimals();

String keyword = request.getParameter("keyword");

    //  APPLY LINEAR SEARCH BEFORE FORWARD
    if (keyword != null && !keyword.trim().isEmpty()) {
        keyword = keyword.trim();   //  remove spaces
        animals = linearSearchByType(animals, keyword);
    }

       // System.out.println("Animals size: " + animals.size());
        request.setAttribute("animals", animals);
        request.setAttribute("animalCount", animals.size());
		request.getRequestDispatcher("/WEB-INF/pages/animal.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
