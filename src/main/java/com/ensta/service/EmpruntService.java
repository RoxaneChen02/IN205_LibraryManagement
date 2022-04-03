package com.ensta.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.ensta.dao.EmpruntDao;
import com.ensta.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class EmpruntService implements IEmpruntService {

	private EmpruntDao empruntDao = EmpruntDao.getInstance();
	private LivreDao livreDao = LivreDao.getInstance();
	private static EmpruntService instance ;
	
	
	public static EmpruntService getInstance() {
		if (instance == null) {
			instance= new EmpruntService();
		}
		return instance;
	}
	
	@Override
	public List<Emprunt> getList() throws ServiceException {
		
		try {
			return empruntDao.getList();
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}
	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		try {
			return empruntDao.getListCurrent();
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		try {
			return empruntDao.getListCurrentByMembre(idMembre);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		try {
			return empruntDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	@Override
	public Emprunt getById(int id) throws ServiceException {
		try {
			return empruntDao.getById(id);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	@Override
	public void create(int idMembre, int idLivre) throws ServiceException {
		try {
			empruntDao.create(idMembre,idLivre);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}
	@Override
	/**
	 * @param id : id de l'emprunt
	 */
	
	public void returnBook(int id) throws ServiceException {
		try {
			Date currentDate = new Date(System.currentTimeMillis());
			Emprunt emprunt = empruntDao.getById(id);
			emprunt.setDateRetour(currentDate.toLocalDate());
			
			empruntDao.update(emprunt);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}
	@Override
	public int count() throws ServiceException {
		try {
			return empruntDao.count();
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	
	
	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		if( instance.getListCurrentByLivre(idLivre).isEmpty()) {
			return true;
		}
		else return false;
	}
	
	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		List<Emprunt> list = instance.getListCurrentByMembre(membre.getId());
		if(list.size() < membre.getAbonnement().getNombre()) return true;
		else return false;
	} 
	
	
}
