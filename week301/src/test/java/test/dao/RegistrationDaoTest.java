package test.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fr.eservices.soaring.dao.RegistrationDao;
import fr.eservices.soaring.dao.RegistrationDaoFactory;
import fr.eservices.soaring.model.Pilote;
import fr.eservices.soaring.model.Repas;

public class RegistrationDaoTest {
	
	RegistrationDao dao = RegistrationDaoFactory.createRegistrationDao();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testFindPilotsByName_match() {
		List<Pilote> pilots = dao.findPilotsByName("Thomas");
		assertNotNull( pilots );
		assertEquals(1, pilots.size());
		
		pilots = dao.findPilotsByName("Leroy");
		assertNotNull( pilots );
		assertEquals(2, pilots.size());
	}
	
	@Test
	public void testFindPilotsByName_none() {
		List<Pilote> pilots = dao.findPilotsByName("UNKNOW");
		assertNotNull( pilots );
		assertEquals(0, pilots.size());
	}
	
	@Test
	public void testFindPilotsBelow_any() throws ParseException {
		Date day = sdf.parse("2016-03-03");
		List<Pilote> pilots = dao.findPilotsBelow(day, 34);
		assertNotNull( pilots );
		assertEquals(2, pilots.size());
	}
	
	@Test
	public void testFindPilotsBelow_one() throws ParseException {
		Date day = sdf.parse("2016-07-31");
		List<Pilote> pilots = dao.findPilotsBelow(day, 21);
		assertNotNull( pilots );
		assertEquals(1, pilots.size());
	}
	
	@Test
	public void testFindPilotsBelow_none() throws ParseException {
		Date day = sdf.parse("2016-12-31");
		List<Pilote> pilots = dao.findPilotsBelow(day, 16);
		assertNotNull( pilots );
		assertEquals(0, pilots.size());
	}
	
	@Test
	public void testFindPilotsByClub() {
		List<Pilote> pilots = dao.findPilotsByClub(2);
		assertNotNull( pilots );
		assertEquals(3, pilots.size());
	}
	
	@Test
	public void testFindPilotsByRegion() {
		List<Pilote> pilots = dao.findPilotsByRegion(1);
		assertNotNull( pilots );
		assertEquals(6, pilots.size());
	}
	
	@Test
	public void testGetAvailableSeatsForLunch_nominal() throws Exception {
		Date day = sdf.parse("2017-07-01");
		Map<Repas, Integer> lunches = dao.getAvailableSeatsForLunch(day, "18:30");
		assertNotNull( lunches );
		assertEquals( 1, lunches.keySet().size() );
		assertEquals( 3, lunches.values().iterator().next().intValue() );
	}
	
	@Test
	public void testGetAvailableSeatsForLunch_full() throws Exception {
		Date day = sdf.parse("2017-07-01");
		Map<Repas, Integer> lunches = dao.getAvailableSeatsForLunch(day, "12:00");
		assertNotNull( lunches );
		assertEquals( 0, lunches.keySet().size() );
	}
	
	@Test
	public void testGetAvailableSeatsForLunch_timeAround() throws Exception {
		Date day = sdf.parse("2017-07-02");
		Map<Repas, Integer> lunches = dao.getAvailableSeatsForLunch(day, "12:00");
		assertNotNull( lunches );
		assertEquals( 2, lunches.keySet().size() );
	}
	
	@Test
	public void testGetAvailableSeatsForLunch_noReservation() throws Exception {
		Date day = sdf.parse("2017-07-02");
		Map<Repas, Integer> lunches = dao.getAvailableSeatsForLunch(day, "19:00");
		assertNotNull( lunches );
		assertEquals( 1,  lunches.keySet().size() );
		assertEquals( 20, lunches.values().iterator().next().intValue() );
	}
	
	@Test
	public void testGetAvailableSeatsForLunch_noNegativeValue() throws Exception {
		Date day = sdf.parse("2017-07-03");
		Map<Repas, Integer> lunches = dao.getAvailableSeatsForLunch(day, "19:00");
		assertNotNull( lunches );
		assertEquals( 0,  lunches.keySet().size() );
	}
	
}
