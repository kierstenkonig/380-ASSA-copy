package edu.ucalgary.oop;

public class Inquirer extends Person{
    private final String INFO;
    private final String SERVICES_PHONE;

    public Inquirer(String firstName, String lastName, String phone, String info) {
        super(firstName, lastName);
        this.SERVICES_PHONE = phone;
        this.INFO = info;
    }

    public String getServicesPhoneNum() { 
        return this.SERVICES_PHONE; 
    }

    public String getInfo() { 
        return this.INFO; 
    }
}
