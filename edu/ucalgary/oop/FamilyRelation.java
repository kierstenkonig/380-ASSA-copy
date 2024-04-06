package edu.ucalgary.oop;

import java.util.Objects;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class FamilyRelation implements Relations {
    private DisasterVictim personOne;
    private String relationship;
    private DisasterVictim personTwo;
    private Map<String, String> relationships = new HashMap<>();


    // Constructor
    public FamilyRelation(DisasterVictim personOne, String relationship, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.relationship = relationship;
        this.personTwo = personTwo;
        // Ensure two-sided relationship
        personOne.getFamilyConnections().add(this);
        personTwo.getFamilyConnections().add(this);
    }

    // Getters and setters
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationshipTo(String relationship) {
        this.relationship = relationship;
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }

    // Implement Relations interface
    @Override
    public void addRelationship(String personOne, String personTwo, String relationship) {
        relationships.put(personOne + "-" + personTwo, relationship);
    }

    @Override
    public void removeRelationship(String relationshipTo) {
        if (this.relationship.equals(relationshipTo)) {
            this.relationship = null;
        }
    }

    @Override
    public boolean isRelated(String personOne, String personTwo, String relationship) {
        String key = personOne + "-" + personTwo;
        return relationships.containsKey(key) && relationships.get(key).equals(relationship);
    }

    @Override
    public List<String> getRelationships() {
        List<String> relationships = new ArrayList<>();
        if (relationship != null) {
            relationships.add(relationship);
        }
        return relationships;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyRelation that = (FamilyRelation) o;
        return (Objects.equals(personOne, that.personOne) && Objects.equals(personTwo, that.personTwo)) ||
               (Objects.equals(personOne, that.personTwo) && Objects.equals(personTwo, that.personOne));
    }


    @Override
    public int hashCode() {
        return Objects.hash(personOne, personTwo) + Objects.hash(personTwo, personOne);
    }
}
