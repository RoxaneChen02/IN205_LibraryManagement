package com.ensta.service;

import java.util.ArrayList;
import java.util.List;

import com.ensta.dao.LivreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;


public class LivreService implements ILivreService {
	
	private LivreDao livreDao = LivreDao.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	private static LivreService instance ; 
	

	
	@Override
	public List<Livre> getList() throws ServiceException {
		
		try {
			return livreDao.getList();
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		List<Livre> list_livre = instance.getList();
		List<Livre> list_livre_dispo = new ArrayList<Livre>() ;
		for(int i =0;i<list_livre.size();++i) {
			if(empruntService.isLivreDispo(list_livre.get(i).getId())==true) 
				list_livre_dispo.add(list_livre.get(i));
		}
		return list_livre_dispo;
	}

	@Override
	public Livre getById(int d) throws ServiceException{
		try {
			return livreDao.getById(d);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
		
	}

	@Override
	public int create(Livre livre) throws ServiceException {
		try {
			if (livre.getTitre()== "")throw new ServiceException();
			else return livreDao.create(livre);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		try {
			if (livre.getTitre()== "")throw new ServiceException();
			else livreDao.update(livre);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			livreDao.delete(id);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public int count() throws ServiceException {
		try {
			return livreDao.count();
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public static LivreService getInstance() {
		if (instance == null) {
			instance= new LivreService();
		}
		return instance;
	}
	
	

}
