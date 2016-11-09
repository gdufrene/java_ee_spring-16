package test.model;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
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

public class ModelAttributes {
	
	@Test public void testClub() {
		Class<?> k = Club.class;
		assertHasField( k, "nom" );
		assertHasField( k, "ville" );
	}
	
	@Test public void testRegion() {
		Class<?> k = Region.class;
		assertHasField( k, "nom" );
	}
	
	@Test public void testReservation() {
		Class<?> k = Reservation.class;
		assertHasField( k, "nbPersonnes" );
	}
	
	@Test public void testRepas() {
		Class<?> k = Repas.class;
		assertHasField( k, "date" );
		assertHasField( k, "heure" );
		assertHasField( k, "menu" );
	}
	
	@Test public void testProduit() {
		Class<?> k = Produit.class;
		assertHasField( k, "libelle" );
		assertHasField( k, "prixUnitaire" );
	}
	
	@Test public void testCategorie() {
		Class<?> k = Categorie.class;
		assertHasField( k, "titre" );
	}
	
	@Test public void testPilote() {
		Class<?> k = Pilote.class;
		assertHasField( k, "nom" );
		assertHasField( k, "prenom" );
		assertHasField( k, "dateNaissance" );
		assertHasField( k, "adresse" );
		assertHasField( k, "codePostal" );
		assertHasField( k, "ville" );
		assertHasField( k, "telPortable" );
	}
	
	@Test public void testVol() {
		Class<?> k = Vol.class;
		assertHasField( k, "date" );
		assertHasField( k, "heureDecollage" );
		assertHasField( k, "heureAtterrissage" );
	}
	
	@Test public void testEpreuve() {
		Class<?> k = Epreuve.class;
		assertHasField( k, "jour" );
		assertHasField( k, "status" );
		assertHasField( k, "type" );
	}
	
	@Test public void testPointPassage() {
		Class<?> k = PointPassage.class;
		assertHasField( k, "nom" );
		assertHasField( k, "lattitude" );
		assertHasField( k, "longitude" );
		assertHasField( k, "description" );
	}
	
	@Test public void testSecteur() {
		Class<?> k = Secteur.class;
		assertHasField( k, "rayon" );
		assertHasField( k, "type" );
	}
	
	@Test public void testProfil() {
		Class<?> k = Profil.class;
		assertHasField( k, "nom" );
	}
	
	@Test public void testUtilisateur() {
		Class<?> k = Utilisateur.class;
		assertHasField( k, "nom" );
	}

	private void assertHasField(Class<?> k, String name) {
		for( Field f : k.getDeclaredFields() ) {
			if ( f.getName().equals(name) ) return;
		}
		fail( "Class " + k.getName() + " must have field " + name );
	}
	

}
