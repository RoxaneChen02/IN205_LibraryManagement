package com.ensta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Abonnement;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class MembreDao implements IMembreDao{

	private static MembreDao instance;
	private MembreDao() {}
	
	public static MembreDao getInstance() {
		if(instance == null) {
			instance= new MembreDao();
		}
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws DaoException {
		try {
			List<Membre> list_membre = new ArrayList<Membre>() ;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT id,nom,prenom,adresse,email,telephone,abonnement FROM Membre ");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next() != false) {
				
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				Abonnement abo = Abonnement.valueOf(rs.getString("abonnement"));
				Membre membre = new Membre(id,nom,prenom,adresse,email,telephone,abo);
				list_membre.add(membre);
			}
			
			
			return list_membre;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}

	}

	@Override
	public Membre getById(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT nom,prenom,adresse,email,telephone,abonnement FROM Membre WHERE id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();  
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String adresse = rs.getString("adresse");
			String email = rs.getString("email");
			String telephone = rs.getString("telephone");
			Abonnement abo = Abonnement.valueOf(rs.getString("abonnement"));
			Membre membre = new Membre(id,nom,prenom,adresse,email,telephone,abo);
			
			return membre;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	
	}

	@Override
	public int create(Membre membre) throws DaoException {
		try {
			
			String nom = membre.getNom();
			String prenom = membre.getPrenom();
			String adresse = membre.getAdresse();
			String email = membre.getEmail();
			String telephone = membre.getTelephone();
			Abonnement abonnement = membre.getAbonnement();
			
			Connection conn = ConnectionManager.getConnection();
			
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Membre(nom,prenom,adresse,email,telephone,abonnement) VALUES ( ?, ?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			stmt.setString(3, adresse);
			stmt.setString(4, email);
			stmt.setString(5,telephone);
			stmt.setString(6, abonnement.toString());

			stmt.executeUpdate();
			
			ResultSet resultSet = stmt.getGeneratedKeys();
			
			if (resultSet.next()) {
				int id = resultSet.getInt(1);
				return id;}
			else return -1;
		
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
	}

	@Override
	public void update(Membre membre) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement stmt = conn.prepareStatement(
				"UPDATE Membre SET nom = ? , prenom = ? , adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?");
			stmt.setString(1, membre.getNom());
			
			stmt.setString(2, membre.getPrenom());
			stmt.setString(3, membre.getAdresse());
			stmt.setString(4, membre.getEmail());
			stmt.setString(5,membre.getTelephone());
			stmt.setString(6, membre.getAbonnement().toString());
			stmt.setInt(7,membre.getId());
			
			stmt.execute();
			
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new DaoException();
		}
		
	}

	@Override
	public void delete(int id) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
				"DELETE FROM Membre WHERE id = ?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		
		
			
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
				"SELECT COUNT(*) as membreCount FROM Membre ");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int membreCount = rs.getInt("membreCount");
			return membreCount;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			throw new DaoException();
		}
		
	}
	
	

}
