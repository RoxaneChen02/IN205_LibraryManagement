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

@WebServlet("/livre_add")

public class LivreAddServlet extends HttpServlet {

	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_add.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String titre = request.getParameter("titre");
			String auteur = request.getParameter("auteur");
			String ISBN = request.getParameter("isbn");
			
			if(titre==""||auteur==""||ISBN =="")response.sendRedirect("/TP3Ensta/livre_add");
			else {
				Livre livre = new Livre(titre,auteur,ISBN);
				int id = livreService.create(livre);
				response.sendRedirect("/TP3Ensta/livre_details?id="+id);
			}
			
		}catch(ServiceException e) {
			e.printStackTrace();
			throw new ServletException();
		}

		
	}
	
}