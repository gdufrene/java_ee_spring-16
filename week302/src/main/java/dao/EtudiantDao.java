package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Etudiant;

public class EtudiantDao {
	
	EntityManager em;
	
	public void setEntityManager( EntityManager em ) {
		this.em = em;
	}
	
	public Etudiant find( int id ) {
		return em.find(Etudiant.class, id);
	}
	
	public Etudiant findByEmail( String email ) {
		
		List<Etudiant> resultat =
			em.createQuery("SELECT e FROM Etudiant e  WHERE email = :mail", Etudiant.class)
			.setParameter("mail", email)
			.getResultList();
		
		if ( resultat.isEmpty() )
			return null;
		
		return resultat.get(0);
	}
	
	public List<Etudiant> olderThan( int age ) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Etudiant> query = cb.createQuery(Etudiant.class);
		
		
		Root<Etudiant> etudiant = query.from( Etudiant.class );
		
		return em.createQuery(
			query.select( etudiant )
			.where( cb.ge(etudiant.get("age"), age) )
		).getResultList();

	}
	
	

}
