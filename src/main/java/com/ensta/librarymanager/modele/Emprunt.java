package com.ensta.librarymanager.modele;
import java.sql.Date;
import java.time.LocalDate;

public class Emprunt {
	private int id;
	private int idMembre;
	private int idLivre;
	private LocalDate dateEmprunt;
	private LocalDate dateRetour;
	
	public Emprunt(int id1, int idMembre1,int idLivre1, LocalDate dateEmprunt1, LocalDate dateRetour1) {
		this.id = id1;
		this.idMembre = idMembre1;
		this.idLivre = idLivre1;
		this.dateEmprunt = dateEmprunt1;
		this.dateRetour = dateRetour1;
	}
	
	public Emprunt(int id1, int idMembre1,int idLivre1, Date dateEmprunt1, Date dateRetour1) {
		this.id = id1;
		this.idMembre = idMembre1;
		this.idLivre = idLivre1;
		this.dateEmprunt = dateEmprunt1.toLocalDate();
		
		if(dateRetour1 != null)this.dateRetour = dateRetour1.toLocalDate();
		else this.dateRetour = null;
		
	}
	
	public Emprunt( int idMembre1,int idLivre1, LocalDate dateEmprunt1, LocalDate dateRetour1) {
		
		this.idMembre = idMembre1;
		this.idLivre = idLivre1;
		this.dateEmprunt = dateEmprunt1;
		this.dateRetour = dateRetour1;
	}
	
	public Emprunt( int idMembre1,int idLivre1, Date dateEmprunt1, Date dateRetour1) {
		
		this.idMembre = idMembre1;
		this.idLivre = idLivre1;
		this.dateEmprunt = dateEmprunt1.toLocalDate();
		if(dateRetour1 != null) this.dateRetour = dateRetour1.toLocalDate();
		else this.dateRetour = null;
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdMembre() {
		return idMembre;
	}
	public void setIdMembre(int idMembre) {
		this.idMembre = idMembre;
	}
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}
	public int getIdLivre() {
		return idLivre;
	}
	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}
	public LocalDate getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	public String toString() {
		return "id : " + id + ", idMembre : " + idMembre + ", idLivre : " + idLivre + ", dateEmprunt : " + dateEmprunt + ", dateRetour : " + dateRetour + " \n";
	}
}
