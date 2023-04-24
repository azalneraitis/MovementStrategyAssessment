package com.example.movementstrategyassessment.models;


// TODO: Convert to JSON import
public enum MovementObservationFinding {
    // Foot and ankle
    FEET_STRAIGHT("Feet: Straight/Parallel"),
    FEET_EXTERNAL_ROTATE("Feet: Externally Rotated"),
    ARCH_NEUTRAL("Arch: Neutral"),
    ARCH_FLATTENED("Arch: Flattened (Pes Planus"),
    ARCH_RAISED("Arch: Raised (Pes Cavus)"),
    LOWER_LEG_VERTICAL("Lower Leg is Vertical"),
    LOWER_LEG_POSTERIOR_DISPLACED("Lower Leg Posteriorly Displaced (Plantar Flexed)");

    private final String label;

    MovementObservationFinding(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }


    }
