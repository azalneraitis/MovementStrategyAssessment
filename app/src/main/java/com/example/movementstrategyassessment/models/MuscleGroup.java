package com.example.movementstrategyassessment.models;

public enum MuscleGroup {
    HAMSTRING("Hamstring"),
    CALF("Calf");

    private final String label;

    MuscleGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
