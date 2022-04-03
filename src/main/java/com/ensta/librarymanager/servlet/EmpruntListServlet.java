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

@WebServlet("/emprunt_list")

public class EmpruntListServlet extends HttpServlet {

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
			String show = request.getParameter("show");
			if (show == null) request.setAttribute("listEmprunt", this.empruntService.getListCurrent());
			else if(show.compareTo("all")==0)request.setAttribute("listEmprunt", this.empruntService.getList());
			this.getServletContext().getRequestDispatcher("/WEB-INF/View/emprunt_list.jsp").forward(request, response);
			
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
			throw new IOException();
		}
		
	}
	
	
}