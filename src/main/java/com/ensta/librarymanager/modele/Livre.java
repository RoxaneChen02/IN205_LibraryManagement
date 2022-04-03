package com.ensta.librarymanager.modele;

public class Livre {
	private int id;
	private String titre;
	private String auteur;
	private String ISBN;
	
	public Livre(int id2, String titre2, String auteur2, String ISBN2) {
		this.id = id2;
		this.titre = titre2;
		this.auteur = auteur2;
		this.ISBN = ISBN2;
	}
	
	public Livre( String titre2, String auteur2, String ISBN2) {
		
		this.titre = titre2;
		this.auteur = auteur2;
		this.ISBN = ISBN2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getIsbn() {
		return ISBN;
	}
	public void setIsbn(String isbn) {
		this.ISBN = isbn;
	}
	
	public String toString() {
		return "id : " + id + " titre : " + titre + " auteur : " + auteur + " isbn : " + ISBN + "\n";
	}
}
