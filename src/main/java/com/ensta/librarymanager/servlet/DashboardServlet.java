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

@WebServlet("/dashboard")

public class DashboardServlet extends HttpServlet {

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
			request.setAttribute("livreCount",this.livreService.count());
			request.setAttribute("membreCount",this.membreService.count());
			request.setAttribute("empruntCount",this.empruntService.count());
			request.setAttribute("listEmprunt", this.empruntService.getListCurrent());
			request.setAttribute("livreInstance", LivreService.getInstance());
			request.setAttribute("membreInstance", MembreService.getInstance());
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/View/dashboard.jsp").forward(request, response);
	}
	
	
}
