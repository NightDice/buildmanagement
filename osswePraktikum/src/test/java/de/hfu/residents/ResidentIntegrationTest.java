package de.hfu.residents;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;
import junit.framework.TestCase;

/* Class stub for integration test purposes
 * The purpose of this stub is to return set values so we can test if functions requiring those values
 * work correctly.
 */
class ResidentRepositoryStub implements ResidentRepository {

	@Override
	public List<Resident> getResidents() {
		
		Resident testResidentOne = new Resident("Peter", "Griffin", "21 Spooner Street", "Quahog", new Date(1960, 7, 4));
		Resident testResidentTwo = new Resident("Lois", "Griffin", "21 Spooner Street", "Quahog", new Date(1963, 12, 5));
		Resident testResidentThree = new Resident("Stewie", "Griffin", "21 Spooner Street", "Quahog", new Date(1995, 4, 20));
		Resident testResidentFour = new Resident("Cleveland", "Brown", "22 Spooner Street", "Quahog", new Date(1961,4,10));
		Resident testResidentThreeDuplicate = new Resident("Stewie", "Griffin", "21 Spooner Street", "Quahog", new Date(1995, 4, 20));
		
		ArrayList<Resident> testResidentList = new ArrayList<Resident>();
		testResidentList.add(testResidentOne);
		testResidentList.add(testResidentTwo);
		testResidentList.add(testResidentThree);
		testResidentList.add(testResidentFour);
		
		// Add Resident created to test for correct handling of duplicate data.
		testResidentList.add(testResidentThreeDuplicate);
		
		return testResidentList;
		
	}
	
}

/* Integration test class.
 * 
 */
public class ResidentIntegrationTest {
	
	public ExpectedException exception = ExpectedException.none();
	
	private BaseResidentService testService = new BaseResidentService();
	private ResidentRepositoryStub repoStub = new ResidentRepositoryStub();
	
	/*
	 * TESTS FOR getFilteredResidentsList()
	 */
	
	/* Test for the getFilteredResidentsList() method.
	 * 
	 */
	@Test
	public void testGetFilteredResidentsListFail() {
		//TODO
	}

	/* Test for getFilteredResidentsList() method.
	 * 
	 */
	@Test
	public void testGetFilteredResidentsListFailTwo() {
		//TODO
	}
	
	/*
	 * TESTS FOR getUniqueResident()
	 */
	
	/* Test for the getUniqueResident() method.
	 * This test attempts to query a Resident with an illegal wildcard argument.
	 * This should trigger a ResidentServiceException.
	 */
	@Test
	public void testGetUniqueResidentWildcardFail() {
		exception.expect(ResidentServiceException.class);
		exception.expectMessage("Wildcards (*) sind nicht erlaubt!");
		
		Resident testFilterResident = new Resident("Lois", "Griffin", "*", "Quahog", new Date(1963, 12, 5));
		
		testService.setResidentRepository(repoStub);
		
		// This solution is not the proper way, but Eclipse refuses to compile it unless I do it this way.
		try {
			testService.getUniqueResident(testFilterResident);
		} catch (ResidentServiceException rse) {
			return;
		}
		fail();
	}
	
	/* Test for getUniqueResident() method.
	 * This test is intended to throw a ResidentServiceException by
	 * querying for a Resident whose data exists twice.
	 */
	@Test
	public void testGetUniqueResidentDuplicateFail() {
		exception.expect(ResidentServiceException.class);
		exception.expectMessage("Suchanfrage lieferte kein eindeutiges Ergebnis!");
		
		Resident testFilterResident = new Resident("Stewie", "Griffin", "21 Spooner Street", "Quahog", new Date(1995, 4, 20));
		
		testService.setResidentRepository(repoStub);
		
		// Same as above, this way is only used to make Eclipse compile, because the proper way doesn't.
		try {
			testService.getUniqueResident(testFilterResident);
		} catch (ResidentServiceException rse) {
			return;
		}
		fail();
	}
	
	/* Test for the getUniqueResident() method.
	 * This test is testing the method to see if with correct input it
	 * returns the correct value. Because the created filter resident is not the same object
	 * as the returned Resident, this cannot use assertSame() and since the equals() method
	 * was not overridden for the Resident class, assertEquals() cannot be used either.
	 */
	@Test
	public void testGetUniqueResidentSuccess() {
		Resident testFilterResident = new Resident("Cleveland", "Brown", "22 Spooner Street", "Quahog", new Date(1961,4,10));
		
		testService.setResidentRepository(repoStub);
		Resident resultResident = new Resident();
		
		try {
			resultResident = testService.getUniqueResident(testFilterResident);
		} catch (ResidentServiceException rse) {
			fail();
		}
		
		assertTrue((resultResident.getGivenName().equals(testFilterResident.getGivenName()))
				&& (resultResident.getFamilyName().equals(testFilterResident.getFamilyName()))
				&& (resultResident.getStreet().equals(testFilterResident.getStreet()))
				&& (resultResident.getCity().equals(testFilterResident.getCity()))
				&& (resultResident.getDateOfBirth().equals(testFilterResident.getDateOfBirth())));
	}

}
