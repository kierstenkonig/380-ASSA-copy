package edu.ucalgary.oop;
import java.util.List;

public interface ReliefServiceInterface {
    public void logInquirer(Inquirer inquirerInfo);
    public List<DisasterVictim> searchDisasterVictim(String partName);
    public void setMode(String mode);
}
