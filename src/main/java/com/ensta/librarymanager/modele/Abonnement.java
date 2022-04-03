package com.ensta.librarymanager.modele;

public enum Abonnement {
	BASIC(2), PREMIUM(5), VIP(20);
	private int nombre;
	
	Abonnement(int i) {
		this.nombre=i;
	}
	
	public int getNombre() {  
        return  this.nombre ;  
   } 
	
}
