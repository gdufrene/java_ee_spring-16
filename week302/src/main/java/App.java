import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.EtudiantDao;
import model.Etudiant;

public class App {
	
	public static void main(String[] args) throws Exception {
		
		EntityManagerFactory emf = null;
		try {
			// create entity manager factory
			emf = Persistence.createEntityManagerFactory("myApp");
			
			// create dao with entity manager
			EtudiantDao dao = new EtudiantDao();
			dao.setEntityManager(emf.createEntityManager());
			
			// get etudiant and display
			
			for( Etudiant e : dao.olderThan(18) ) {
				System.out.println( e );
			}
			
			
		} finally {		
			// close emf !
			emf.close();
		}
	}

}
