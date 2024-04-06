package edu.ucalgary.oop.Tests;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.DisasterVictim;
import edu.ucalgary.oop.FamilyRelation;
import edu.ucalgary.oop.GenderList;
import edu.ucalgary.oop.Location;
import edu.ucalgary.oop.MedicalRecord;
import edu.ucalgary.oop.Person;
import edu.ucalgary.oop.Supply;
import edu.ucalgary.oop.meals;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class DisasterVictimTest {
    private static final String GENDER_OPTIONS_FILE = "/Users/kierstenkonig/Desktop/380-ASSA/edu/ucalgary/oop/GenderOptions.txt";
    private List<String> genderOptions;

    private DisasterVictim victim;

    @Test
    public void testConstructor() {
        victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        assertEquals("John", victim.getFirstName());
        assertEquals("Doe", victim.getLastName());
        assertEquals("1990-01-01", victim.getDateOfBirth());
        assertEquals("2020-01-01", victim.getEntryDate());
    }

    @Test
    public void testConstructorNoLastName() {
        victim = new DisasterVictim("John", "2020-01-01");
        assertEquals("John", victim.getFirstName());
        assertEquals("", victim.getLastName());
        assertEquals("2020-01-01", victim.getEntryDate());
    }

    @Test
    public void testConstructorInvalidDateOfBirth() {
        try {
            victim = new DisasterVictim("John", "Doe", "cat", "2020-01-01");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid date format for date of birth. Expected format: YYYY-MM-DD", e.getMessage());
        }
    }

    @Test
    public void testConstructorInvalidEntryDate() {
        try {
            victim = new DisasterVictim("John", "Doe", "1990-01-01", "cat");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid date format for entry date. Expected format: YYYY-MM-DD", e.getMessage());
        }
    }

    @Test
    public void testGetFirstName() {
        victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        assertEquals("John", victim.getFirstName());
    }

    @Test
    public void testGetLastName() {
        victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        assertEquals("Doe", victim.getLastName());
    }

    @Test
    public void testGetDateOfBirth() {
        victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        assertEquals("1990-01-01", victim.getDateOfBirth());
    }

    @Test
    public void testGetEntryDate() {
        victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        assertEquals("2020-01-01", victim.getEntryDate());
    }

    @Test
    public void testSetFirstName() {
        victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        victim.setFirstName("Jane");
        assertEquals("Jane", victim.getFirstName());
    }

    @Test
    public void testSetLastName() {
        victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        victim.setLastName("Doe");
        assertEquals("Doe", victim.getLastName());
    }

    @Test
    public void testSetDateOfBirthWhenAgeIsNotSet() {
        victim = new DisasterVictim("John", "2020-01-01");
        victim.setDateOfBirth("1990-01-01");
        assertEquals("1990-01-01", victim.getDateOfBirth());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWhenAgeIsSet() {
        victim = new DisasterVictim("John", "2020-01-01");
        victim.setAge(30); // Set some age
        victim.setDateOfBirth("1990-01-01"); // Try setting date of birth again
    }


    @Test
    public void testGetAssignedSocialID() {
       
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId, actualVictim.getAssignedSocialID());
    }

    // @Test
    // public void testGetFamilyConnections(){
    //     DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
    //     DisasterVictim personTwo = new DisasterVictim("Jane", "Smith", "1995-01-01", "2020-01-01");
    //     FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
    //     List<FamilyRelation> familyRelations = new ArrayList<>();
    //     familyRelations.add(familyRelation);
    //     personOne.setFamilyConnections(familyRelations);
    //     assertEquals(familyRelations, personOne.getFamilyConnections());
    // }

    @Test
    public void testGetMedicalRecords(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        MedicalRecord medicalRecord = new MedicalRecord(new Location("Calgary", "Alberta"), "Broken leg", "2020-01-01");
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        victim.setMedicalRecords(medicalRecords);
        assertEquals(medicalRecords, victim.getMedicalRecords());
    }

    @Test
    public void testGetPersonalBelongings(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        Supply supply = new Supply("Water", 5);
        List<Supply> supplies = new ArrayList<>();
        supplies.add(supply);
        victim.setPersonalBelongings(supplies);
        assertEquals(supplies, victim.getPersonalBelongings());
    }

    @Test
    public void testGetAge(){
        DisasterVictim victim = new DisasterVictim("John", "2020-01-01");
        victim.setAge(30);
        assertEquals(30, victim.getAge());
    }
    
    @Test
    public void testSetAge() {
        DisasterVictim victim = new DisasterVictim("John", "2020-01-01");
        victim.setAge(30);
        assertEquals(30, victim.getAge());
        try {
            victim.setDateOfBirth("1990-01-01");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    // @Test
    // public void testSetFamilyConnections(){
    //     DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
    //     DisasterVictim personTwo = new DisasterVictim("Jane", "Smith", "1995-01-01", "2020-01-01");
    //     FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
    //     List<FamilyRelation> familyRelations = new ArrayList<>();
    //     familyRelations.add(familyRelation);
    //     personOne.setFamilyConnections(familyRelations);
    //     assertEquals(familyRelations, personOne.getFamilyConnections());
    // }

    @Test
    public void testSetMedicalRecords(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        MedicalRecord medicalRecord = new MedicalRecord(new Location("Calgary", "Alberta"), "Broken leg", "2020-01-01");
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        victim.setMedicalRecords(medicalRecords);
        assertEquals(medicalRecords, victim.getMedicalRecords());
    }

    @Test
    public void testSetPersonalBelongings(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        Supply supply = new Supply("Water", 5);
        List<Supply> supplies = new ArrayList<>();
        supplies.add(supply);
        victim.setPersonalBelongings(supplies);
        assertEquals(supplies, victim.getPersonalBelongings());
    }

    @Test
    public void testAddPersonalBelonging(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        Supply supply = new Supply("Water", 5);
        victim.addPersonalBelonging(supply);
        List<Supply> supplies = new ArrayList<>();
        supplies.add(supply);
        assertEquals(supplies, victim.getPersonalBelongings());
    }

    @Test
    public void testRemovePersonalBelonging(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        Supply supply = new Supply("Water", 5);
        victim.addPersonalBelonging(supply);
        victim.removePersonalBelonging(supply);
        List<Supply> supplies = new ArrayList<>();
        assertEquals(supplies, victim.getPersonalBelongings());
    }

    // @Test
    // public void testRemoveFamilyConnection(){
    //     DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
    //     DisasterVictim personTwo = new DisasterVictim("Jane", "Smith", "1995-01-01", "2020-01-01");
    //     FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
    //     List<FamilyRelation> familyRelations = new ArrayList<>();
    //     familyRelations.add(familyRelation);
    //     personOne.setFamilyConnections(familyRelations);
    //     personOne.removeFamilyConnection(familyRelation);
    //     List<FamilyRelation> emptyList = new ArrayList<>();
    //     assertEquals(emptyList, personOne.getFamilyConnections());
    // } 

    // @Test
    // public void testAddFamilyConnection(){
    //     DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
    //     DisasterVictim personTwo = new DisasterVictim("Jane", "Smith", "1995-01-01", "2020-01-01");
    //     FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
    //     personOne.addFamilyConnection(familyRelation);
    //     List<FamilyRelation> familyRelations = new ArrayList<>();
    //     familyRelations.add(familyRelation);
    //     assertEquals(familyRelations, personOne.getFamilyConnections());
    // }

    @Test
    public void testAddMedicalRecord(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        MedicalRecord medicalRecord = new MedicalRecord(new Location("Calgary", "Alberta"), "Broken leg", "2020-01-01");
        victim.addMedicalRecord(medicalRecord);
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        assertEquals(medicalRecords, victim.getMedicalRecords());
    }

    @Test
    public void testGetComments(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        String comment = "This is a comment";
        victim.setComments(comment);
        assertEquals(comment, victim.getComments());
    }

    @Test
    public void testSetComments(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        String comment = "This is a comment";
        victim.setComments(comment);
        assertEquals(comment, victim.getComments());
    }


    // @Test
    // public void testEnterRelationship() {
    //     DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
    //     DisasterVictim personTwo = new DisasterVictim("Jane", "Smith", "1995-01-01", "2020-01-01");
    //     FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
    //     List<FamilyRelation> familyConnections = new ArrayList<>();
    //     familyConnections.add(familyRelation);
    //     personOne.enterRelationship(familyConnections);
    //     assertEquals(familyConnections, personOne.getFamilyConnections());
    //     assertEquals(familyConnections, personTwo.getFamilyConnections());
    // }

    @Test
    public void testEnterMedialRecords() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        MedicalRecord medicalRecord = new MedicalRecord(new Location("Calgary", "Alberta"), "Broken leg", "2020-01-01");
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecords.add(medicalRecord);
        victim.enterMedialRecords(medicalRecords);
        assertEquals(medicalRecords, victim.getMedicalRecords());
    }

    @Test
    public void testGetLocation() {
        // Arrange
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        Location expectedLocation = new Location("Calgary", "Alberta");
        victim.setLocation(expectedLocation); 
        Location actualLocation = victim.getLocation();
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testSetLocation() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        Location expectedLocation = new Location("Calgary", "Alberta");
        victim.setLocation(expectedLocation); 
        Location actualLocation = victim.getLocation();
        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testGetMealPreference(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        meals mealPreference = meals.VGML;
        victim.setMealPreference(mealPreference);
        assertEquals(mealPreference, victim.getMealPreference());
    }

    @Test
    public void testSetMealPreference(){
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        meals mealPreference = meals.VGML;
        victim.setMealPreference(mealPreference);
        assertEquals(mealPreference, victim.getMealPreference());
    }


    @Test
    public void testGetGenderOptions() {
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        genderOptions = GenderList.loadGenderOptionsFromFile(GENDER_OPTIONS_FILE);
        assertNotNull(genderOptions);
        assertFalse(genderOptions.isEmpty());
    }

    @Test
    public void testSetGenderOptions() {
        List<String> genderOptions = GenderList.loadGenderOptionsFromFile(GENDER_OPTIONS_FILE);
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        victim.setGenderOptions(genderOptions);
        assertEquals(genderOptions, victim.getGenderOptions());
    }

    @Test
    public void testValidateGender() {
        List<String> genderOptions = GenderList.loadGenderOptionsFromFile(GENDER_OPTIONS_FILE);
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        victim.setGenderOptions(genderOptions);
        assertTrue("Gender validation should pass for 'man'", victim.validateGender("Man"));
    }

    @Test
    public void testGetGender() {
        List<String> genderOptions = GenderList.loadGenderOptionsFromFile(GENDER_OPTIONS_FILE);
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        victim.setGenderOptions(genderOptions);
        victim.setGender("man");
        assertEquals("man", victim.getGender());
    }

    @Test
    public void testSetGender() {
        List<String> genderOptions = GenderList.loadGenderOptionsFromFile(GENDER_OPTIONS_FILE);
        DisasterVictim victim = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        victim.setGenderOptions(genderOptions);
        victim.setGender("man");
        assertEquals("man", victim.getGender());
        try {
            victim.setGender("invalid");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testSearchPartialName() {
        Person praveen = new Person("Praveen", "Kumar");
        Person oprah = new Person("Oprah", "Winfrey");
        Person bob = new Person("Bob", "Smith");

        List<Person> people = new ArrayList<>();
        people.add(praveen);
        people.add(oprah);
        people.add(bob);

        List<Person> result = Person.search(people, "Pra");

        assertTrue(result.contains(praveen));
        assertTrue(result.contains(oprah));
        assertFalse(result.contains(bob));
    }

    @Test
    public void testGetRelationships() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2020-01-01");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
        List<FamilyRelation> familyRelations = new ArrayList<>();
        familyRelations.add(familyRelation);
        personOne.setFamilyConnections(familyRelations);
        assertEquals(familyRelations, personOne.getFamilyConnections());
    }

    @Test
    public void testSetRelationships() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2020-01-01");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
        List<FamilyRelation> familyRelations = new ArrayList<>();
        familyRelations.add(familyRelation);
        personOne.setFamilyConnections(familyRelations);
        assertEquals(familyRelations, personOne.getFamilyConnections());
    }

    @Test
    public void testAddRelationship() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Smith", "1995-01-01", "2020-01-01");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
        personOne.addFamilyConnection(familyRelation);
        List<FamilyRelation> familyRelations = new ArrayList<>();
        familyRelations.add(familyRelation);
        assertEquals(familyRelations, personOne.getFamilyConnections());
    }

    @Test
    public void testRemoveRelationship() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2020-01-01");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
        List<FamilyRelation> familyRelations = new ArrayList<>();
        familyRelations.add(familyRelation);
        personOne.setFamilyConnections(familyRelations);
        personOne.removeFamilyConnection(familyRelation);
        List<FamilyRelation> emptyList = new ArrayList<>();
        assertEquals(emptyList, personOne.getFamilyConnections());
    }

    @Test
    public void testIsRelated() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2020-01-01");
        personOne.addRelationship(personOne.getLastName(), personTwo.getLastName(), "spouse");
        boolean isRelated = personOne.isRelated(personOne.getLastName(), personTwo.getLastName(), "spouse");
        assertTrue("isRelated should return true if relationship exists", isRelated);
    }

    @Test
    public void testAddFamilyConnection() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2020-01-01");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
        personOne.addFamilyConnection(familyRelation);
        List<FamilyRelation> familyRelations = new ArrayList<>();
        familyRelations.add(familyRelation);
        assertEquals(familyRelations, personOne.getFamilyConnections());
    }

    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2020-01-01");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
        List<FamilyRelation> familyRelations = new ArrayList<>();
        familyRelations.add(familyRelation);
        personOne.setFamilyConnections(familyRelations);
        personOne.removeFamilyConnection(familyRelation);
        List<FamilyRelation> emptyList = new ArrayList<>();
        assertEquals(emptyList, personOne.getFamilyConnections());
    }

    @Test
    public void testEnterRelationship() {
        DisasterVictim personOne = new DisasterVictim("John", "Doe", "1990-01-01", "2020-01-01");
        DisasterVictim personTwo = new DisasterVictim("Jane", "Doe", "1995-01-01", "2020-01-01");
        FamilyRelation familyRelation = new FamilyRelation(personOne, "spouse", personTwo);
        List<FamilyRelation> familyConnections = new ArrayList<>();
        familyConnections.add(familyRelation);
        personOne.enterRelationship(familyConnections);
        assertEquals(familyConnections, personOne.getFamilyConnections());
        assertEquals(familyConnections, personTwo.getFamilyConnections());
    }



}



