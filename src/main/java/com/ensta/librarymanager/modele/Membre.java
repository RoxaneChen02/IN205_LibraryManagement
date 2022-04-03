package com.ensta.librarymanager.modele;

public class Membre {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String telephone;
	private Abonnement  abonnement;
	
	public Membre(int id1,String nom1,String prenom1,String adresse1,String email1,String telephone1,Abonnement abonnement1) {
		this.id = id1;
		this.nom = nom1;
		this.prenom = prenom1;
		this.adresse =adresse1;
		this.email  = email1;
		this.telephone = telephone1;
		this.abonnement = abonnement1;
	}
	
	public Membre(String nom1,String prenom1,String adresse1,String email1,String telephone1,String abonnement1) {
		
		this.nom = nom1;
		this.prenom = prenom1;
		this.adresse =adresse1;
		this.email  = email1;
		this.telephone = telephone1;
		this.abonnement = Abonnement.valueOf(abonnement1);
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Abonnement getAbonnement() {
		return abonnement;
	}
	public void setAbonnement(Abonnement abonnement) {
		this.abonnement = abonnement;
	}
	
	public String toString() {
		return " id : " + id + "; nom : " + nom + " ; prenom : " + prenom + " ; adresse : " + adresse + " ; email : " + email + " ; telephone : "+telephone + " ; abonnement : " +abonnement + "\n";
	}
}
