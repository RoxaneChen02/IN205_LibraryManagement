package com.ensta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.persistence.ConnectionManager;

public class LivreDao implements ILivreDao{
	
	private static LivreDao instance;
	private LivreDao() {}
	
	public static LivreDao getInstance() {
		if(instance == null) {
			instance= new LivreDao();
		}
		return instance;
	}
	
	
	@Override
	public Livre getById(int id) throws DaoException{
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT titre, auteur, ISBN FROM Livre WHERE id = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();  
			String titre = rs.getString("titre");
			String auteur = rs.getString("auteur");
			String ISBN = rs.getString("ISBN");
			
			Livre livre = new Livre(id, titre, auteur, ISBN);
			return livre;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		
		
	}


	@Override
	public List<Livre> getList() throws DaoException {
		try {
			List<Livre> list_livre = new ArrayList<Livre>() ;
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT id,titre, auteur, ISBN FROM Livre ");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next() != false) {
				
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String ISBN = rs.getString("ISBN");
				Livre livre = new Livre(id, titre, auteur, ISBN);
				list_livre.add(livre);
			}
			
			
			return list_livre;
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
		
	}

	@Override
	public int create(Livre livre) throws DaoException {
		
		try {
			
			String auteur = livre.getAuteur();
			String titre = livre.getTitre();
			String isbn = livre.getIsbn();
			
			Connection conn = ConnectionManager.getConnection();
			
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO Livre( titre, auteur, ISBN) VALUES ( ?, ?,?)",Statement.RETURN_GENERATED_KEYS);
			
			
			stmt.setString(1, titre);
			stmt.setString(2, auteur);
			stmt.setString(3, isbn);

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
	public void update(Livre livre) throws DaoException {
		Connection conn;
		try {
			conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
				"UPDATE Livre SET titre = ? , auteur = ? , ISBN = ? WHERE id = ?");
			pstmt.setString(1, livre.getTitre());
			pstmt.setString(2, livre.getAuteur());
			pstmt.setString(3, livre.getIsbn());
			pstmt.setInt(4, livre.getId());
			pstmt.execute();
			
		
			
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
				"DELETE FROM Livre WHERE id = ?");
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
				"SELECT COUNT(*) as livreCount FROM Livre ");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int livreCount = rs.getInt("livreCount");
			return livreCount;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DaoException();
		}
		
	}
}
