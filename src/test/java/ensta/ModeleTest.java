package ensta;

import java.sql.Date;
import java.time.LocalDate;

import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;

public class ModeleTest {
	
	public static void main(String[] args) {
		Livre livre = new Livre("Nouveau livre","Roxane","1232");
		System.out.print(livre);
	
		Membre membre = new Membre("Roxane","Chen","Allée des techniques avancées","roxane@gmail.com","064309","VIP");
		System.out.print(membre);
		
		LocalDate dateEmprunt = new Date(System.currentTimeMillis()).toLocalDate();
		LocalDate dateRetour = new Date(System.currentTimeMillis()).toLocalDate();
		
		Emprunt emprunt = new Emprunt(2,3,dateEmprunt,dateRetour);
		System.out.print(emprunt);
	}
	
	
}
