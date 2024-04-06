package edu.ucalgary.oop;
import java.util.List;

public interface ReliefWorker {
    public void enterDisasterVictimInfo(String firstName, String lastName, String comments, int assignedSocialId, String gender);
    public void enterRelationship(List<FamilyRelation> familyConnections);
    public void enterMedialRecords(List<MedicalRecord> medicalRecords);
    public void setLocation(Location location);

}