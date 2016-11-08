package fr.eservices.soaring.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import fr.eservices.soaring.model.Club;
import fr.eservices.soaring.model.Pilote;
import fr.eservices.soaring.model.Region;
import fr.eservices.soaring.model.Repas;

public interface RegistrationDao {
	
	public List<Pilote> findPilotsByName(String nom);
	public List<Pilote> findPilotsByRegion(int id_region);
	public List<Pilote> findPilotsByClub(int id_club);
	
	/**
	 * Only year of birth is considered.
	 * ie, we are on july 11th 2016, asks for pilots below 25, pilots born on 1991 and under are returned.
	 * @param currentDate : would be new Date(), but could be different for test purpose.
	 * @param age is any integer between 0 and 100
	 * @return
	 */
	public List<Pilote> findPilotsBelow(Date currentDate, int age);
	
	/**
	 * Search for Lunch around a given day and time.  
	 * Would return lunch from time -2H until time +2H
	 * @param day as a java Date
	 * @param time would be a string made of HH:mm (24h format) 
	 * @return map as Lunch -> available seats
	 */
	public Map<Repas, Integer> getAvailableSeatsForLunch(Date day, String time);
	

	
}
