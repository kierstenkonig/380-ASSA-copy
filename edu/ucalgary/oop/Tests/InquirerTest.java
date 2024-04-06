package edu.ucalgary.oop.Tests;

import org.junit.Test;

import edu.ucalgary.oop.Inquirer;

import org.junit.*;
import static org.junit.Assert.*;


public class InquirerTest {
    private Inquirer inquirer;

    @Test
    public void testInquirerConstructor() {
        String firstName = "John";
        String lastName = "Doe";
        String phone = "123-456-7890";
        String info = "Test info";
    
        Inquirer inquirer = new Inquirer(firstName, lastName, phone, info);
    
        assertEquals("First name should match the one set", firstName, inquirer.getFirstName());
        assertEquals("Last name should match the one set", lastName, inquirer.getLastName());
        assertEquals("Phone should match the one set", phone, inquirer.getServicesPhoneNum());
        assertEquals("Info should match the one set", info, inquirer.getInfo());
    }


    @Test
    public void testGetServicesPhoneNum() {
        String firstName = "John";
        String lastName = "Doe";
        String phone = "123-456-7890";
        String info = "Test info";
    
        Inquirer inquirer = new Inquirer(firstName, lastName, phone, info);
    
        assertEquals("Phone should match the one set", phone, inquirer.getServicesPhoneNum());
    }

    @Test
    public void testGetInfo() {
        String firstName = "John";
        String lastName = "Doe";
        String phone = "123-456-7890";
        String info = "Test info";
    
        Inquirer inquirer = new Inquirer(firstName, lastName, phone, info);
    
        assertEquals("Info should match the one set", info, inquirer.getInfo());
    }


}
