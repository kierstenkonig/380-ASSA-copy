package edu.ucalgary.oop;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public class DisasterVictim extends Person implements ReliefWorker, Relations{
    private static int counter = 0;

    private String dateOfBirth;
    private final int ASSIGNED_SOCIAL_ID;
    private List<FamilyRelation> familyConnections = new ArrayList<>();
        private Map<String, String> relationships = new HashMap<>();
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    private List<Supply> personalBelongings;
    private final String ENTRY_DATE;
    private String gender;
    private List<String> genderOptions = GenderList.getGenderOptions();
    private String comments;
    private int age;
    private Location location;
    private meals mealPreference;


    public DisasterVictim(String firstName, String lastName, String dateOfBirth, String ENTRY_DATE) {
        super(firstName, lastName);
        if (!isValidDateFormat(dateOfBirth)) {
            throw new IllegalArgumentException("Invalid date format for date of birth. Expected format: YYYY-MM-DD");
        }
        this.dateOfBirth = dateOfBirth;
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }

    public DisasterVictim(String firstName, String ENTRY_DATE) {
        super(firstName, "");
        if (!isValidDateFormat(ENTRY_DATE)) {
            throw new IllegalArgumentException("Invalid date format for entry date. Expected format: YYYY-MM-DD");
        }
        this.ENTRY_DATE = ENTRY_DATE;
        this.ASSIGNED_SOCIAL_ID = generateSocialID();
    }


    private static int generateSocialID() {
        counter++;
        return counter;
    }

    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return date.matches(dateFormatPattern);
    }

  
    // Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (age != 0) {
            throw new IllegalArgumentException("Age is already set. Cannot set date of birth.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public int getAssignedSocialID() {
        return ASSIGNED_SOCIAL_ID;
    }

    public List<FamilyRelation> getFamilyConnections() {
        return this.familyConnections;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return this.medicalRecords;
    }

    public List<Supply> getPersonalBelongings() {
        return this.personalBelongings;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (dateOfBirth != null) {
            throw new IllegalArgumentException("Date of birth is already set. Cannot set age.");
        }
        this.age = age;
    }

    // The add and remove methods remain correct.
    
    // Correct the setters to accept Lists instead of arrays
    public void setFamilyConnections(List<FamilyRelation> familyConnections) {
        this.familyConnections.clear();
        for (FamilyRelation newRelation : familyConnections) {
            addFamilyConnection(newRelation);
        }
    }

    public void setMedicalRecords(List<MedicalRecord> records) {
        this.medicalRecords.clear();
        for (MedicalRecord newRecord : records) {
            addMedicalRecord(newRecord);
        }
    }

    public void setPersonalBelongings(List<Supply> tmpSupply) {
        this.personalBelongings = tmpSupply;
    }

    public void addPersonalBelonging(Supply supply) {
        if (this.personalBelongings == null) {
            this.personalBelongings = new ArrayList<>();
        }
        this.personalBelongings.add(supply);
    }

    // Remove a Supply from personalBelongings
    public void removePersonalBelonging(Supply unwantedSupply) {
        if (this.personalBelongings != null) {
            this.personalBelongings.remove(unwantedSupply);
        }
    }

    // Add a MedicalRecord to medicalRecords
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public String getEntryDate() {
        return ENTRY_DATE;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments =  comments;
    }

    public String getGender() {
        return gender;
    }

    // genderOptions
    public List<String> getGenderOptions() {
        return genderOptions;
    }

    public void setGenderOptions(List<String> genderOptions ) {
        this.genderOptions = genderOptions;
    }

    public boolean validateGender(String gender) {
        // Check if gender is in genderOptions
        return genderOptions.stream().anyMatch(option -> option.equalsIgnoreCase(gender));
    }

    public void setGender(String gender) {
        if (validateGender(gender)) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException() ;
        }
    }


    public meals getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(meals mealPreference) {
        this.mealPreference = mealPreference;
    }


    @Override
    public void enterDisasterVictimInfo(String firstName, String lastName, String comments, int assignedSocialId, String gender){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setComments(comments);
        this.setGender(gender);
    }

    // @Override
    // public void enterRelationship(List<FamilyRelation> familyConnections) {
    //     for (FamilyRelation relation : familyConnections) {
    //         this.addFamilyConnection(relation);
    //         relation.getPersonTwo().addFamilyConnection(relation);
    //     }
    // }

    @Override
    public void enterMedialRecords(List<MedicalRecord> medicalRecords) {
        for (MedicalRecord record : medicalRecords) {
            this.addMedicalRecord(record);
        }
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    } 

    @Override
    public List<String> getRelationships() {
        List<String> relationships = new ArrayList<>();
        for (FamilyRelation relation : familyConnections) {
            relationships.addAll(relation.getRelationships());
        }
        return relationships;
    }
    

    @Override
    public void addRelationship(String personOne, String personTwo, String relationship) {
        String key = personOne + "-" + personTwo;
        relationships.put(key, relationship);
    }

    @Override
    public void removeRelationship(String relationship) {
        for (FamilyRelation relation : familyConnections) {
            relation.removeRelationship(relationship);
        }
    }

    @Override
    public boolean isRelated(String personOne, String personTwo, String relationship) {
        String key = personOne + "-" + personTwo;
        return relationships.containsKey(key) && relationships.get(key).equals(relationship);
    }
    
    public void addFamilyConnection(FamilyRelation relation) {
        if (!familyConnections.contains(relation)) {
            familyConnections.add(relation);
        }
    }

    public void removeFamilyConnection(FamilyRelation relation) {
        familyConnections.remove(relation);
    }
    
    @Override
    public void enterRelationship(List<FamilyRelation> familyConnections) {
        for (FamilyRelation relation : familyConnections) {
            if (!this.familyConnections.contains(relation)) {
                this.addFamilyConnection(relation);
            }
            if (!relation.getPersonTwo().familyConnections.contains(relation)) {
                relation.getPersonTwo().addFamilyConnection(relation);
            }
        }
    }

}





