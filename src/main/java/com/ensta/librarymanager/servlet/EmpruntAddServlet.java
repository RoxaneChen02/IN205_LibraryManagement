package com.ensta.librarymanager.servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.service.EmpruntService;
import com.ensta.service.LivreService;
import com.ensta.service.MembreService;

@WebServlet("/emprunt_add")

public class EmpruntAddServlet extends HttpServlet {

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
			request.setAttribute("listLivreDispo", this.livreService.getListDispo());
			request.setAttribute("listMembrePossible", this.membreService.getListMembreEmpruntPossible());
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
			throw new ServletException();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String idMembreString = request.getParameter("idMembre");
			String idLivreString = request.getParameter("idLivre");
			
			
			if(idMembreString != null && idLivreString != null) {
				int idMembre = Integer.parseInt(idMembreString);
				int idLivre = Integer.parseInt(idLivreString);
				empruntService.create(idMembre, idLivre);
				response.sendRedirect("/TP3Ensta/emprunt_list");
			}
			else throw new ServletException();
			
		}
		catch (ServiceException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		
	}
	
}