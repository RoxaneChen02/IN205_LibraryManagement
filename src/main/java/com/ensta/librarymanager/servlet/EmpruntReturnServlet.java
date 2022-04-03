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

@WebServlet("/emprunt_return")

public class EmpruntReturnServlet extends HttpServlet {

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
			
			request.setAttribute("livreInstance", this.livreService);
			request.setAttribute("membreInstance", this.membreService);
			request.setAttribute("empruntInstance", this.empruntService);
			request.setAttribute("listEmpruntCurrent", this.empruntService.getListCurrent());
			String idString = request.getParameter("id");
			int id=-1;
			Boolean test = false;
			if (idString != null) {
				test = true ; 
				id=Integer.parseInt(idString);
				request.setAttribute("titreLivreSelected", this.livreService.getById(empruntService.getById(id).getIdLivre()).getTitre());
				request.setAttribute("prenomMembreSelected",this.membreService.getById(empruntService.getById(id).getIdMembre()).getPrenom());
				request.setAttribute("nomMembreSelected", this.membreService.getById(empruntService.getById(id).getIdMembre()).getNom());
			}
			request.setAttribute("test",test);
		    request.setAttribute("id",id);
		   

		} catch (ServiceException e) {
			
			e.printStackTrace();
			throw new ServletException();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String idString = request.getParameter("id");
			
			if(idString!=null) {
				int id = Integer.parseInt(idString);
				try {
					empruntService.returnBook(id);
					response.sendRedirect("/TP3Ensta/emprunt_list");
				} catch (ServiceException e) {
					e.printStackTrace();
					throw new ServletException();
				}
			}
			
			else throw new ServletException();
	
	}
	
}