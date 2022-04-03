package com.ensta.librarymanager.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.service.EmpruntService;
import com.ensta.service.LivreService;
import com.ensta.service.MembreService;

@WebServlet("/livre_details")

public class LivreDetailsServlet extends HttpServlet {

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
				request.setAttribute("listEmprunt", this.empruntService.getListCurrentByLivre(id));
				request.setAttribute("id", id);
				Livre livre = livreService.getById(id);
				request.setAttribute("titre", livre.getTitre());
				request.setAttribute("auteur", livre.getAuteur());
				request.setAttribute("isbn", livre.getIsbn());
			}
			
			
		
		} catch (ServiceException e) {
			
			e.printStackTrace();
			throw new IOException();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_details.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			String idString = request.getParameter("id");
			if(idString!=null) {
				int id = Integer.parseInt(idString);
				try {
					String titre = request.getParameter("titre");
					String auteur = request.getParameter("auteur");
					String isbn = request.getParameter("isbn");
					Livre livre = livreService.getById(id);
					livre.setAuteur(auteur);
					livre.setIsbn(isbn);
					livre.setTitre(titre);
					livreService.update(livre);
					response.sendRedirect("/TP3Ensta/livre_details?id="+id);
				} catch (ServiceException e) {
					e.printStackTrace();
					throw new ServletException();
				}

			}

	}}