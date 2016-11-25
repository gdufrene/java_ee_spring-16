package test.model;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.eservices.soaring.model.Categorie;
import fr.eservices.soaring.model.Club;
import fr.eservices.soaring.model.Epreuve;
import fr.eservices.soaring.model.Pilote;
import fr.eservices.soaring.model.PointPassage;
import fr.eservices.soaring.model.Produit;
import fr.eservices.soaring.model.Profil;
import fr.eservices.soaring.model.Region;
import fr.eservices.soaring.model.Repas;
import fr.eservices.soaring.model.Reservation;
import fr.eservices.soaring.model.Secteur;
import fr.eservices.soaring.model.Utilisateur;
import fr.eservices.soaring.model.Vol;

public class JpaAnnotations {

	private static Class<? extends Annotation> 
		idAnnotation,
		entityAnnotation,
		manyToOneAnnotation,
		manyToManyAnnotation,
		oneToManyAnnotation,
		joinColumnAnnotation,
		inheritanceAnnotation;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void getJpaClasses() throws ClassNotFoundException {
		idAnnotation          = (Class<? extends Annotation>) Class.forName("javax.persistence.Id");
		entityAnnotation      = (Class<? extends Annotation>) Class.forName("javax.persistence.Entity");
		manyToOneAnnotation   = (Class<? extends Annotation>) Class.forName("javax.persistence.ManyToOne");
		manyToManyAnnotation  = (Class<? extends Annotation>) Class.forName("javax.persistence.ManyToMany");
		oneToManyAnnotation   = (Class<? extends Annotation>) Class.forName("javax.persistence.OneToMany");
		joinColumnAnnotation  = (Class<? extends Annotation>) Class.forName("javax.persistence.JoinColumn");
		inheritanceAnnotation = (Class<? extends Annotation>) Class.forName("javax.persistence.Inheritance");
	}
	
	@Test public void testPilote() {
		Class<?> k = Pilote.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
		
		assertHasAssociation( k, Club.class, manyToOneAnnotation );
	}	
	
	@Test public void testClub() {
		Class<?> k = Club.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
		
		assertHasAssociation( k, Region.class, manyToOneAnnotation );
	}	

	@Test public void testRegion() {
		Class<?> k = Region.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
	}
	
	@Test public void testReservation() {
		Class<?> k = Reservation.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 2, idAnnotation );
		
		assertHasAssociation( k, Pilote.class, manyToOneAnnotation );
		assertHasAssociation( k, Pilote.class, joinColumnAnnotation );
		
		assertHasAssociation( k, Repas.class, manyToOneAnnotation );
		assertHasAssociation( k, Repas.class, joinColumnAnnotation );
	}
	
	@Test public void testRepas() {
		Class<?> k = Repas.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasAnnotation( k, inheritanceAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
	}
	
	@Test public void testProduit() {
		Class<?> k = Produit.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
		
		assertHasAssociation( k, Categorie.class, manyToOneAnnotation );
	}
	
	@Test public void testCategorie() {
		Class<?> k = Categorie.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
	}
	
	@Test public void testVol() {
		Class<?> k = Vol.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
		
		assertHasAssociation( k, Pilote.class, manyToOneAnnotation );
		assertHasAssociation( k, Epreuve.class, manyToOneAnnotation );
	}
	
	@Test public void testEpreuve() {
		Class<?> k = Epreuve.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
	}
	
	@Test public void testPointPassage() {
		Class<?> k = PointPassage.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
	}
	
	@Test public void testSecteur() {
		Class<?> k = Secteur.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 2, idAnnotation );
		
		assertHasAssociation( k, Epreuve.class, manyToOneAnnotation );
		assertHasAssociation( k, Epreuve.class, joinColumnAnnotation );
		
		assertHasAssociation( k, PointPassage.class, manyToOneAnnotation );
		assertHasAssociation( k, PointPassage.class, joinColumnAnnotation );
	}
	
	@Test public void testProfil() {
		Class<?> k = Profil.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
	}
	
	@Test public void testUtilisateur() {
		Class<?> k = Utilisateur.class;
		assertHasAnnotation( k, entityAnnotation );
		assertHasNbAnnotation( k, 1, idAnnotation );
		
		assertHasAssociation( k, Profil.class, manyToOneAnnotation );
	}

	private void assertHasAnnotation(Class<?> k, Class<?> annotation) {
		for ( Annotation ann : k.getAnnotations() ) {
			if ( ann.annotationType() == annotation ) return;
		}
		fail( "Class " + k.getName() + " must have annotation @" + annotation.getName() );
	}
	
	private void assertHasNbAnnotation(Class<?> k, int i, Class<?> annotation) {
		int nb = 0;
		for ( Field f : k.getDeclaredFields() ) {
			for ( Annotation ann : f.getAnnotations() ) {
				if ( ann.annotationType() == annotation ) nb++;
			}
		}
		assertEquals("Class " + k.getName() + " must have " + i + " fiels annotated with @" + annotation.getName(), i, nb );
	}
	
	private void assertHasAssociation(Class<?> k, Class<?> type, Class<? extends Annotation> annotation) {
		for ( Field f : k.getDeclaredFields() ) {
			if ( f.getType() != type ) continue;
			if ( f.getAnnotation(annotation) != null ) return;
		}
		fail( "Class " + k.getName() + " must have association @" + annotation.getName() + " to " + type.getName() );
	}
}
