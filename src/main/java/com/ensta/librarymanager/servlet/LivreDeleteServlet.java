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

@WebServlet("/livre_delete")

public class LivreDeleteServlet extends HttpServlet {

	LivreService livreService = LivreService.getInstance();
	MembreService membreService = MembreService.getInstance();
	EmpruntService empruntService = EmpruntService.getInstance();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
	
		if(idString!=null) {
			int id = Integer.parseInt(idString);
			
			try {
				request.setAttribute("id", id);
				Livre livre = livreService.getById(id);
				
				request.setAttribute("titre", livre.getTitre());
				request.setAttribute("auteur", livre.getAuteur());
				request.setAttribute("isbn", livre.getIsbn());
				
			} catch (ServiceException e) {
				
				e.printStackTrace();
				throw new ServletException();
			}
			
		}
		
		else throw new ServletException();
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_delete.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String idString = request.getParameter("id");
			
			if(idString != null) {
				int id = Integer.parseInt(idString);
				livreService.delete(id);
				response.sendRedirect("/TP3Ensta/livre_list");
			}
			
			
			else throw new ServletException();
			
		}catch(ServiceException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		
	}
	
}