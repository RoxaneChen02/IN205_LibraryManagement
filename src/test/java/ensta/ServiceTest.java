
package ensta;
import java.sql.Date;

import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.service.EmpruntService;
import com.ensta.service.LivreService;
import com.ensta.service.MembreService;

public class ServiceTest {
	
	public static void main(String[] args) throws DaoException, ServiceException {
		LivreService livreService= LivreService.getInstance();
		MembreService membreService = MembreService.getInstance();
		EmpruntService empruntService = EmpruntService.getInstance();
		
		try {
			
			livreService.delete(1);
			Livre livre = livreService.getById(2);
			livre.setTitre("newtitre");
			livreService.update(livre);
			Livre newlivre = new Livre("Nouveau livre","Roxane","1232");
			int newid = livreService.create(newlivre);
			//System.out.print(livreService.getList());
			//System.out.print(newid + "\n");
			
			Membre newmembre = new Membre("nom","prenom","adresse","eùail","tel","VIP");
			Membre membre = membreService.getById(2);
			membre.setNom("newtitre");
			membreService.update(membre);
			//System.out.print(membreService.getList());
			
			
			Emprunt emprunt1 = empruntService.getById(2);
			empruntService.create(3,5);
			//System.out.print(empruntService.getListCurrent());
			empruntService.returnBook(3);
			//System.out.print(empruntService.isLivreDispo(10));
			//System.out.print(empruntService.isEmpruntPossible(membreService.getById(2)));
			//System.out.print(empruntService.getListCurrentByMembre(5));
			//System.out.print(empruntService.getList());
			//System.out.print(membreService.getListMembreEmpruntPossible());
		}
		catch (ServiceException e) {
			
			e.printStackTrace();
		}
	}

}