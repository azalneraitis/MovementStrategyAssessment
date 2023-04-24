package com.example.movementstrategyassessment.models;

import java.util.ArrayList;
import java.util.List;

// TODO: Convert to JSON import
public enum StaticObservationFinding {
    // Foot and ankle
    FEET_STRAIGHT("Feet: Straight/Parallel"),
    FEET_EXTERNAL_ROTATE("Feet: Externally Rotated"),
    ARCH_NEUTRAL("Arch: Neutral"),
    ARCH_FLATTENED("Arch: Flattened (Pes Planus"),
    ARCH_RAISED("Arch: Raised (Pes Cavus)"),
    LOWER_LEG_VERTICAL("Lower Leg is Vertical"),
    LOWER_LEG_POSTERIOR_DISPLACED("Lower Leg Posteriorly Displaced (Plantar Flexed)"),

    // Knee
    KNEE_IN_LINE("Knee Inline w/ 2nd & 3rd Toes"),
    KNEE_VALGUS(""),
    KNEE_VARUS(""),
    KNEE_STRAIGHT(""),
    KNEE_FLEXED(""),
    KNEE_HYPEREXTENDED(""),

    // LPHC
    PELVIS_LEVEL(""),
    PELVIS_ANTERIOR_TILT(""),
    PELVIS_POSTERIOR_TILT(""),
    HIPS_NEUTRAL(""),
    HIPS_FLEXED(""),
    HIPS_EXTENDED(""),
    LSPINE_NORMAL(""),
    LSPINE_EXC_LORDOSIS(""),
    LSPINE_RED_LORDOSIS(""),
    LSPINE_LATERAL_SHIFT(""),

    // Shoulder and T-spine
    SHOULDER_LEVEL(""),
    SHOULDER_ELEVATE(""),
    SHOULDER_INLINE_HIPS_EARS(""),
    SHOULDER_ROUNDED(""),
    TSPINE_NORMAL(""),
    TSPINE_EXC_KYPHOSIS(""),

    // Head and C-spine
    HEAD_NEUTRAL(""),
    HEAD_FORWARD(""),
    CSPINE_NORMAL("");

    private final String label;

    StaticObservationFinding(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static List<StaticObservationFinding> getAnteriorObservations() {
        List<StaticObservationFinding> ret = new ArrayList<>();

        ret.add(StaticObservationFinding.FEET_STRAIGHT);
        ret.add(StaticObservationFinding.FEET_EXTERNAL_ROTATE);
        ret.add(StaticObservationFinding.KNEE_IN_LINE);
        ret.add(StaticObservationFinding.KNEE_VALGUS);
        ret.add(StaticObservationFinding.KNEE_VARUS);
        ret.add(StaticObservationFinding.PELVIS_LEVEL);
        ret.add(StaticObservationFinding.SHOULDER_LEVEL);
        ret.add(StaticObservationFinding.SHOULDER_ELEVATE);
        ret.add(StaticObservationFinding.SHOULDER_ROUNDED);
        ret.add(StaticObservationFinding.HEAD_NEUTRAL);

        return ret;
    }

    public static List<StaticObservationFinding> getLateralObservations() {
        List<StaticObservationFinding> ret = new ArrayList<>();

        ret.add(StaticObservationFinding.LOWER_LEG_VERTICAL);
        ret.add(StaticObservationFinding.LOWER_LEG_POSTERIOR_DISPLACED);
        ret.add(StaticObservationFinding.KNEE_STRAIGHT);
        ret.add(StaticObservationFinding.KNEE_FLEXED);
        ret.add(StaticObservationFinding.KNEE_HYPEREXTENDED);
        ret.add(StaticObservationFinding.PELVIS_ANTERIOR_TILT);
        ret.add(StaticObservationFinding.PELVIS_POSTERIOR_TILT);
        ret.add(StaticObservationFinding.HIPS_NEUTRAL);
        ret.add(StaticObservationFinding.HIPS_FLEXED);
        ret.add(StaticObservationFinding.HIPS_EXTENDED);
        ret.add(StaticObservationFinding.LSPINE_NORMAL);
        ret.add(StaticObservationFinding.LSPINE_EXC_LORDOSIS);
        ret.add(StaticObservationFinding.LSPINE_RED_LORDOSIS);
        ret.add(StaticObservationFinding.TSPINE_NORMAL);
        ret.add(StaticObservationFinding.TSPINE_EXC_KYPHOSIS);
        ret.add(StaticObservationFinding.SHOULDER_INLINE_HIPS_EARS);
        ret.add(StaticObservationFinding.HEAD_FORWARD);
        ret.add(StaticObservationFinding.CSPINE_NORMAL);

        return ret;
    }

    public static List<StaticObservationFinding> getPosteriorObservations() {
        List<StaticObservationFinding> ret = new ArrayList<>();

        ret.add(StaticObservationFinding.FEET_STRAIGHT);
        ret.add(StaticObservationFinding.FEET_EXTERNAL_ROTATE);
        ret.add(StaticObservationFinding.ARCH_NEUTRAL);
        ret.add(StaticObservationFinding.ARCH_FLATTENED);
        ret.add(StaticObservationFinding.ARCH_RAISED);
        ret.add(StaticObservationFinding.KNEE_IN_LINE);
        ret.add(StaticObservationFinding.KNEE_VALGUS);
        ret.add(StaticObservationFinding.KNEE_VARUS);
        ret.add(StaticObservationFinding.LSPINE_LATERAL_SHIFT);
        ret.add(StaticObservationFinding.SHOULDER_LEVEL);
        ret.add(StaticObservationFinding.SHOULDER_ELEVATE);
        ret.add(StaticObservationFinding.SHOULDER_ROUNDED);
        ret.add(StaticObservationFinding.HEAD_NEUTRAL);

        return ret;
    }
}
