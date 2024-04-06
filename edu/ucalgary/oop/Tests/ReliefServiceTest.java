package edu.ucalgary.oop.Tests;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.DisasterVictim;
import edu.ucalgary.oop.Inquirer;
import edu.ucalgary.oop.Location;
import edu.ucalgary.oop.ReliefService;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReliefServiceTest {
    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;
    private String validDate = "2024-02-10";
    private String invalidDate = "2024/02/10";
    private String expectedInfoProvided = "Looking for family member";
    private String expectedLogDetails = "Inquirer: John, Missing Person: Jane Alex, Date of Inquiry: 2024-02-10, Info Provided: Looking for family member, Last Known Location: University of Calgary"; 

    @Before
    public void setUp() {
        // Assuming Inquirer, DisasterVictim, and Location have constructors as implied
        inquirer = new Inquirer("John", "Alex", "1234567890", "Looking for family member");
        missingPerson = new DisasterVictim("Jane Alex", "2024-01-25");
        lastKnownLocation = new Location("University of Calgary", "2500 University Dr NW");
        reliefService = new ReliefService(inquirer, missingPerson, validDate, expectedInfoProvided, lastKnownLocation);
    }

    @Test
    public void testObjectCreation() {
        assertNotNull("ReliefService object should not be null", reliefService);
    }

    @Test
    public void testGetInquirer() {
        assertEquals("Inquirer should match the one set in setup", inquirer, reliefService.getInquirer());
    }

    @Test
    public void testGetMissingPerson() {
        assertEquals("Missing person should match the one set in setup", missingPerson, reliefService.getMissingPerson());
    }

    @Test
    public void testGetDateOfInquiry() {
        assertEquals("Date of inquiry should match the one set in setup", validDate, reliefService.getDateOfInquiry());
    }

    @Test
    public void testGetInfoProvided() {
        assertEquals("Info provided should match the one set in setup", expectedInfoProvided, reliefService.getInfoProvided());
    }

    @Test
    public void testGetLastKnownLocation() {
        assertEquals("Last known location should match the one set in setup", lastKnownLocation, reliefService.getLastKnownLocation());
    }

    @Test
    public void testSetDateOfInquiryWithValidDate() {
        reliefService.setDateOfInquiry(validDate);
        assertEquals("Setting a valid date should update the date of inquiry", validDate, reliefService.getDateOfInquiry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryWithInvalidDate() {
        reliefService.setDateOfInquiry(invalidDate); // This should throw IllegalArgumentException due to invalid format
    }

    @Test
    public void testGetLogDetails() {
        assertEquals("Log details should match the expected format", expectedLogDetails, reliefService.getLogDetails());
    }

    @Test
    public void testLogInquirer(){
        reliefService.logInquirer(inquirer);
        assertEquals("Log details should match the expected format", expectedLogDetails, reliefService.getLogDetails());
    }

    @Test
    public void testSearchDisasterVictim() {
        DisasterVictim missingPerson = new DisasterVictim("Jane", "2024-01-25");
        reliefService.addDisasterVictim(missingPerson);
        String partName = "jan";
        List<DisasterVictim> expectedResults = Arrays.asList(missingPerson);
        List<DisasterVictim> actualResults = reliefService.searchDisasterVictim(partName);
        assertEquals("Search results should match the expected results", expectedResults, actualResults);
    }


    @Test
    public void testGetDisasterVictim() {
        DisasterVictim missingPerson = new DisasterVictim("Jane", "2024-01-25");
        reliefService.addDisasterVictim(missingPerson);
        List<DisasterVictim> expectedResults = Arrays.asList(missingPerson);
        List<DisasterVictim> actualResults = reliefService.getDisasterVictim();
        assertEquals("Disaster victims should match the expected results", expectedResults, actualResults);
    }

    @Test
    public void testAddDisasterVictim() {
        DisasterVictim missingPerson = new DisasterVictim("Jane", "2024-01-25");
        reliefService.addDisasterVictim(missingPerson);
        List<DisasterVictim> expectedResults = Arrays.asList(missingPerson);
        List<DisasterVictim> actualResults = reliefService.getDisasterVictim();
        assertEquals("Disaster victims should match the expected results", expectedResults, actualResults);
    }

    @Test
    public void testGetMode(){
        String mode = "test";
        reliefService.setMode(mode);
        assertNull("Mode should be null when the input is invalid", reliefService.getMode());
    }

    @Test
    public void testSetMode() {
        String mode = "test";
        reliefService.setMode(mode);
        assertNull("Mode should be null when the input is invalid", reliefService.getMode());
    }

}
