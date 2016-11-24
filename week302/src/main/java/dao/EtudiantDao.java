package dao;

import javax.persistence.EntityManager;

import model.Etudiant;

public class EtudiantDao {
	
	EntityManager em;
	
	public void setEntityManager( EntityManager em ) {
		this.em = em;
	}
	
	public Etudiant find( int id ) {
		return em.find(Etudiant.class, id);
	}
	
	

}
