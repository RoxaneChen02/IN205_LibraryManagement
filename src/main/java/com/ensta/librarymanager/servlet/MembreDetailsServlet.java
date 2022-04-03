package com.ensta.librarymanager.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.service.EmpruntService;
import com.ensta.service.LivreService;
import com.ensta.service.MembreService;

@WebServlet("/membre_details")

public class MembreDetailsServlet extends HttpServlet {

	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			

			request.setAttribute("livreInstance", livreService);
			request.setAttribute("membreInstance", membreService);
			
			String idString = request.getParameter("id");
			int id;
			if(idString!=null) {
				id = Integer.parseInt(idString);
				
				request.setAttribute("id", id);
				Membre membre = membreService.getById(id);

				request.setAttribute("nom", membre.getNom());
				request.setAttribute("prenom", membre.getPrenom());
				request.setAttribute("adresse", membre.getAdresse());
				request.setAttribute("email", membre.getEmail());
				request.setAttribute("telephone", membre.getTelephone());
				request.setAttribute("abonnement", membre.getAbonnement());
				request.setAttribute("listEmprunt", empruntService.getListCurrentByMembre(id));
			}
			
			
		
		} catch (ServiceException e) {
			
			e.printStackTrace();
			throw new IOException();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_details.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			String idString = request.getParameter("id");
			if(idString!=null) {
				int id = Integer.parseInt(idString);
				try {
					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					String adresse = request.getParameter("adresse");
					String email = request.getParameter("email");
					String telephone = request.getParameter("telephone");
					String abonnement = request.getParameter("abonnement");
					Membre membre = membreService.getById(id);
					
					membre.setNom(nom);
					membre.setPrenom(prenom);
					membre.setAdresse(adresse);
					membre.setEmail(email);
					membre.setTelephone(telephone);
					membre.setAbonnement(Abonnement.valueOf(abonnement));
					
					membreService.update(membre);
					response.sendRedirect("/TP3Ensta/membre_details?id="+id);
				} catch (ServiceException e) {
					e.printStackTrace();
					throw new ServletException();
				}

			}

	}}