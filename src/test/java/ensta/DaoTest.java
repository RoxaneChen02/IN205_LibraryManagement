
package ensta;
import java.sql.Date;
import java.time.LocalDate;

import com.ensta.dao.EmpruntDao;
import com.ensta.dao.LivreDao;
import com.ensta.dao.MembreDao;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;


public class DaoTest {
	
	public static void main(String[] args) throws DaoException{
		LivreDao livreDao= LivreDao.getInstance();
		MembreDao membreDao = MembreDao.getInstance();
		EmpruntDao empruntDao = EmpruntDao.getInstance();
		
		try {
			
			livreDao.delete(1);
			Livre livre = livreDao.getById(2);
			livre.setTitre("newtitre");
			livreDao.update(livre);
			Livre newlivre = new Livre("Nouveau livre","Roxane","1232");
			int newid = livreDao.create(newlivre);
			//System.out.print(livreDao.getList());
			//System.out.print(newid + "\n");
			
			Membre newmembre = new Membre("nom","prenom","adresse","eùail","tel","VIP");
			Membre membre = membreDao.getById(2);
			membre.setNom("newtitre");
			membreDao.update(membre);
			//System.out.print(membreDao.getList());
			
			
			LocalDate dateRetour = new Date(System.currentTimeMillis()).toLocalDate();
			Emprunt emprunt1 = empruntDao.getById(2);
			emprunt1.setDateRetour(dateRetour);
			empruntDao.update(emprunt1);
			empruntDao.create(2, 4);
			
			System.out.print(empruntDao.getList());
			
			
			
		}
		catch (DaoException e) {
			
			e.printStackTrace();
		}
	}

}