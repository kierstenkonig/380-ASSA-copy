package edu.ucalgary.oop;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Person {
    protected String firstName;
    protected String lastName;
    protected String name;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = firstName + " " + lastName;
    }

    public Person(ResultSet resultSet) {
        try {
            this.firstName = resultSet.getString("first_name");
            this.lastName = resultSet.getString("last_name");
            this.name = this.firstName + " " + this.lastName;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public static List<Person> search(List<Person> people, String query) {
        List<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (person.getFirstName().toLowerCase().contains(query.toLowerCase()) || person.getLastName().toLowerCase().contains(query.toLowerCase())) {
                result.add(person);
            }
        }
        return result;
    }
    
}


// java -cp .:/Users/kierstenkonig/Desktop/postgresql-42.7.3.jar edu.ucalgary.oop.ReliefServiceGUI
//  javac -cp .:/Users/kierstenkonig/Desktop/postgresql-42.7.3.jar edu/ucalgary/oop/*.java