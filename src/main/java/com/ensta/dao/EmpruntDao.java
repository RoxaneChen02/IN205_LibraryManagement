package com.ensta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class EmpruntDao implements IEmpruntDao {
	
	private static EmpruntDao instance;
	private EmpruntDao() {}
	
	public static EmpruntDao getInstance() {
		if(instance == null) {
			instance= new EmpruntDao();
		}
		return instance;
	}
	
	
	@Override
	public List<Emprunt> getList() throws DaoException {
		try {
			List<Emprunt> list_emprunt = new ArrayList<Emprunt>() ;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, "
					+ "ISBN, dateEmprunt, dateRetour "
					+ " FROM emprunt AS e"
					+ " INNER JOIN membre ON membre.id = e.idMembre"
					+ " INNER JOIN livre ON livre.id = e.idLivre"
					+ " ORDER BY dateRetour DESC;");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next() != false) {
				
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				int idLivre = rs.getInt("idLivre");
				Date dateEmprunt = rs.getDate("dateEmprunt");
				Date dateRetour = rs.getDate("dateRetour");
				
				Emprunt emprunt = new Emprunt(id, idMembre,idLivre,dateEmprunt,dateRetour );
				list_emprunt.add(emprunt);
			}
			
			
			return list_emprunt;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
	}
	
	

	@Override
	public List<Emprunt> getListCurrent() throws DaoException {
		try {
			
			
			List<Emprunt> list_emprunt = new ArrayList<Emprunt>() ;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT e.id AS id, idMembre, nom, prenom, adresse, email,"
					+ "telephone, abonnement, idLivre, titre, auteur,ISBN, dateEmprunt,dateRetour"
					+ " FROM emprunt AS e"
					+ " INNER JOIN membre ON membre.id = e.idMembre"
					+ " INNER JOIN livre ON livre.id = e.idLivre"
					+ " WHERE dateRetour IS NULL ;");
			
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next() != false) {
				
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				int idLivre = rs.getInt("idLivre");
				Date dateEmprunt = rs.getDate("dateEmprunt");
				Date dateRetour = rs.getDate("dateRetour");
				
				
				
				Emprunt emprunt = new Emprunt(id, idMembre,idLivre,dateEmprunt,dateRetour );
				list_emprunt.add(emprunt);
			}
			
			
			return list_emprunt;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
		try {
			List<Emprunt> list_emprunt = new ArrayList<Emprunt>() ;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT e.id AS id, idMembre, nom, prenom, adresse, email,"
					+ "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,"
					+ "dateRetour"
					+ " FROM emprunt AS e"
					+ " INNER JOIN membre ON membre.id = e.idMembre"
					+ " INNER JOIN livre ON livre.id = e.idLivre"
					+ " WHERE dateRetour IS NULL AND membre.id = ?;");
			pstmt.setInt(1, idMembre);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next() != false) {
				
				int id = rs.getInt("id");
				
				int idLivre = rs.getInt("idLivre");
				Date dateEmprunt = rs.getDate("dateEmprunt");
				Date dateRetour = rs.getDate("dateRetour");
				
				Emprunt emprunt = new Emprunt(id, idMembre,idLivre,dateEmprunt,dateRetour );
				list_emprunt.add(emprunt);
			}
			
			
			return list_emprunt;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
		try {
			List<Emprunt> list_emprunt = new ArrayList<Emprunt>() ;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT e.id AS id, idMembre, nom, prenom, adresse, email,"
					+" telephone, abonnement, idLivre, titre, auteur, ISBN, dateEmprunt,"
					+" dateRetour"
					+" FROM emprunt AS e"
					+" INNER JOIN membre ON membre.id = e.idMembre"
					+" INNER JOIN livre ON livre.id = e.idLivre"
					+" WHERE dateRetour IS NULL AND livre.id = ?;");
			
			pstmt.setInt(1, idLivre);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next() != false) {
				
				int id = rs.getInt("id");
				int idMembre = rs.getInt("idMembre");
				
				Date dateEmprunt = rs.getDate("dateEmprunt");
				Date dateRetour = rs.getDate("dateRetour");
				
				Emprunt emprunt = new Emprunt(id, idMembre,idLivre,dateEmprunt,dateRetour );
				list_emprunt.add(emprunt);
			}
			
			
			return list_emprunt;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public Emprunt getById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email,"
					+ " telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt,"
					+ "dateRetour"
					+" FROM emprunt AS e"
					+ " INNER JOIN membre ON membre.id = e.idMembre"
					+ " INNER JOIN livre ON livre.id = e.idLivre" 
					+" WHERE e.id = ?;");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();  
			int idMembre = rs.getInt("idMembre");
			int idLivre = rs.getInt("idLivre");
			Date dateEmprunt = rs.getDate("dateEmprunt");
			Date dateRetour = rs.getDate("dateRetour");
			
			Emprunt emprunt= new Emprunt(id, idMembre,idLivre,dateEmprunt,dateRetour );
			return emprunt;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void create(int idMembre, int idLivre) throws DaoException {
		try {
			
			LocalDate dateEmprunt = new Date(System.currentTimeMillis()).toLocalDate();
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Emprunt(idMembre,idLivre,dateEmprunt,dateRetour) VALUES ( ?, ?,?,?)");
			
			
			stmt.setInt(1, idMembre);
			stmt.setInt(2, idLivre);
			stmt.setDate(3, Date.valueOf(dateEmprunt));
			stmt.setDate(4, null);
			
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public void update(Emprunt emprunt) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE emprunt"
					+ "  SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ?"
					+ "WHERE id = ?;");
			pstmt.setInt(1, emprunt.getIdMembre());
			pstmt.setInt(2, emprunt.getIdLivre());
			pstmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
			pstmt.setDate(4, Date.valueOf(emprunt.getDateRetour()));
			pstmt.setInt(5, emprunt.getId());
			pstmt.execute();
			
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public int count() throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
				"SELECT COUNT(id) as empruntCount FROM Emprunt ");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int empruntCount = rs.getInt("empruntCount");
			return empruntCount;
			
		} catch (SQLException e) {

			e.printStackTrace();		
			throw new DaoException();
		}
	}

}
