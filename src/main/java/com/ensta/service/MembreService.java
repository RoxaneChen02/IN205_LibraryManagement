package com.ensta.service;

import java.util.ArrayList;
import java.util.List;


import com.ensta.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class MembreService implements IMembreService {

	private MembreDao membreDao = MembreDao.getInstance();
	private EmpruntService empruntService = EmpruntService.getInstance();
	private static MembreService instance ; 
	
	public static MembreService getInstance() {
		if (instance == null) {
			instance= new MembreService();
		}
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws ServiceException {
		try {
			return membreDao.getList();
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		List<Membre> list_membre = instance.getList();
		List<Membre> list_membre_possible = new ArrayList<Membre>() ;
		for(int i =0;i<list_membre.size();++i) {
			if(empruntService.isEmpruntPossible(list_membre.get(i))==true) 
				list_membre_possible.add(list_membre.get(i));
		}
		return list_membre_possible;
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		try {
			return membreDao.getById(id);
		} catch (DaoException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int create(Membre membre)
			throws ServiceException {
		try {
			if (membre.getNom().compareTo("")==0 || membre.getPrenom().compareTo("")==0) throw new ServiceException();
			else {
				membre.setNom(membre.getNom().toUpperCase());
				return membreDao.create(membre);
			}
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		try {
			if (membre.getNom().compareTo("")==0 || membre.getPrenom().compareTo("")==0) throw new ServiceException();
			else {
				membre.setNom(membre.getNom().toUpperCase());
				membreDao.update(membre);
			}
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			membreDao.delete(id);
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
		
	}

	@Override
	public int count() throws ServiceException {
		try {
			return membreDao.count();
		} catch (DaoException e) {
			
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
