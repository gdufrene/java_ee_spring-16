import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.EtudiantDao;
import model.Etudiant;

public class App {
	
	public static void main(String[] args) throws Exception {
		// create entity manager factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		
		// create dao with entity manager
		EtudiantDao dao = new EtudiantDao();
		dao.setEntityManager(emf.createEntityManager());
		
		// get etudiant and display
		Etudiant etudiant = dao.find(1);
		System.out.println(etudiant);
		
		// close emf !
		emf.close();
	}

}
