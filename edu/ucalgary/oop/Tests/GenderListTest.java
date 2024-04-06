package edu.ucalgary.oop.Tests;

import org.junit.Before;
import org.junit.Test;

import edu.ucalgary.oop.GenderList;

import java.util.List;

import static org.junit.Assert.*;

public class GenderListTest {
    private static final String GENDER_OPTIONS_FILE = "/Users/kierstenkonig/Desktop/380-ASSA/edu/ucalgary/oop/GenderOptions.txt";
    private List<String> genderOptions;
    @Before
    public void setUp() {
        genderOptions = GenderList.loadGenderOptionsFromFile(GENDER_OPTIONS_FILE);
    }

    @Test
    public void testLoadGenderOptionsFromFile() {
        // Assuming GenderOptions.txt contains at least one option
        assertNotNull(genderOptions);
        assertFalse(genderOptions.isEmpty());
    }
}
