package com.ensta.librarymanager.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;

import com.ensta.librarymanager.modele.Membre;
import com.ensta.service.EmpruntService;
import com.ensta.service.LivreService;
import com.ensta.service.MembreService;

@WebServlet("/membre_add")

public class MembreAddServlet extends HttpServlet {

	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_add.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adresse = request.getParameter("adresse");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			
			if(nom==""||prenom=="") {
				response.sendRedirect("/TP3Ensta/membre_add");
			}
			else {
				Membre membre = new Membre(nom,prenom,adresse,email,telephone,"BASIC");
				int id = membreService.create(membre);
				response.sendRedirect("/TP3Ensta/membre_details?id="+id);
			}
			
		}catch(ServiceException e) {
			e.printStackTrace();
			throw new ServletException();
		}

		
	}
	
}