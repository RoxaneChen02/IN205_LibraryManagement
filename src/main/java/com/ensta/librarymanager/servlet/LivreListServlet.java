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

@WebServlet("/livre_list")

public class LivreListServlet extends HttpServlet {

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
			request.setAttribute("listLivre", livreService.getList());
			
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
			throw new ServletException();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/livre_list.jsp").forward(request, response);
	}
	
	
	
}