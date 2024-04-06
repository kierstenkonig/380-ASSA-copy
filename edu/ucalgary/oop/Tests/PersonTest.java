package edu.ucalgary.oop.Tests;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.Person;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;


public class PersonTest {
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
}
