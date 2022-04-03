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

@WebServlet("/membre_delete")

public class MembreDeleteServlet extends HttpServlet {

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
				Membre membre = membreService.getById(id);
				
				request.setAttribute("nom", membre.getNom());
				request.setAttribute("prenom", membre.getPrenom());
				
				
			} catch (ServiceException e) {
				
				e.printStackTrace();
				throw new ServletException();
			}
			
		}
		
		else throw new ServletException();
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/membre_delete.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String idString = request.getParameter("id");
			
			if(idString != null) {
				int id = Integer.parseInt(idString);
				membreService.delete(id);
				response.sendRedirect("/TP3Ensta/membre_list");
			}
			
			
			else throw new ServletException();
			
		}catch(ServiceException e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		
	}
	
}