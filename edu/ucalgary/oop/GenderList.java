package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenderList {
    private static final String GENDER_OPTIONS_FILE = "/Users/kierstenkonig/Desktop/380-ASSA/edu/ucalgary/oop/GenderOptions.txt";

    private static List<String> genderOptions;

    static {
        genderOptions = loadGenderOptionsFromFile(GENDER_OPTIONS_FILE);
    }

    public static List<String> loadGenderOptionsFromFile(String filename) {
        List<String> options = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                options.add(line.trim()); // Trim spaces
            }
        } catch (IOException e) {
            // Handle file not found or other IO exceptions gracefully
            System.err.println("Error reading gender options from file: " + e.getMessage());
        }
        return options;
    }

    public static List<String> getGenderOptions() {
        return genderOptions;
    }
}
