package edu.ucalgary.oop;

import java.util.List;

public interface Relations {
    void addRelationship(String personOne, String personTwo, String relationship);
    void removeRelationship(String relationship);
    boolean isRelated(String personOne, String personTwo, String relationship);
    List<String> getRelationships();
}