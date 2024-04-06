package edu.ucalgary.oop;

public enum meals{
    
    AVML("Asian vegetarian meal"),
    DBML("Diabetic meal"), 
    GFML("Gluten intolerant meal"),
    KSML("Kosher meal"),
    LSML("Low salt meal"),
    MOML("Muslim meal"),
    PFML("Peanut-free meal"),
    VGML("Vegan meal"),
    VJML("Vegetarian Jain meal");

    private final String description;

    meals(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
