package edu.ucalgary.oop.Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

import edu.ucalgary.oop.DisasterVictim;
import edu.ucalgary.oop.FamilyRelation;

public class FamilyRelationTest {

    private DisasterVictim personOne = new DisasterVictim("John Dalan", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane Dalan", "2024-02-20");
    private FamilyRelation testFamilyRelationObject = new FamilyRelation(personOne, "sibling", personTwo);
    
    @Test
    public void testObjectCreation() {
        assertNotNull(testFamilyRelationObject);
    }
    
    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        testFamilyRelationObject.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());
    }

    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        testFamilyRelationObject.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo", newPersonTwo, testFamilyRelationObject.getPersonTwo());
    }

    @Test
    public void testSetAndGetRelationshipTo() {
        String newRelationship = "parent";
        testFamilyRelationObject.setRelationshipTo(newRelationship);
        assertEquals("setRelationshipTo should update relationshipTo", newRelationship, testFamilyRelationObject.getRelationship());
    }

    @Test
    public void testAddRelationship() {
        testFamilyRelationObject.addRelationship("John Dalan", "Jane Dalan", "sibling");
        assertEquals("addRelationship should update relationship", "sibling", testFamilyRelationObject.getRelationship());
    }

    @Test
    public void testRemoveRelationship() {
        testFamilyRelationObject.removeRelationship("sibling");
        assertNull("removeRelationship should set relationshipTo to null", testFamilyRelationObject.getRelationship());
    }

    @Test
    public void testIsRelated() {
        testFamilyRelationObject.addRelationship("John Dalan", "Jane Dalan", "sibling");
        boolean isRelated = testFamilyRelationObject.isRelated("John Dalan", "Jane Dalan", "sibling");
        assertTrue("isRelated should return true if relationship exists", isRelated);
    }


    @Test
    public void testGetRelationships() {
        List<String> relationships = testFamilyRelationObject.getRelationships();
        assertTrue("getRelationships should return a list containing relationship", relationships.contains("sibling"));
    }

    
}
