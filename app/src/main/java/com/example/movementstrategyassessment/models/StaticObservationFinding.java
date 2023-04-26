package com.example.movementstrategyassessment.models;

import java.util.ArrayList;
import java.util.List;

// TODO: Convert to JSON import
public enum StaticObservationFinding {
    // Foot and ankle
    //FEET_STRAIGHT("Feet: Straight/Parallel"),
    FEET_EXTERNAL_ROTATE("Feet: Externally Rotated"),
   // ARCH_NEUTRAL("Arch: Neutral"),
    ARCH_FLATTENED("Arch: Flattened (Pes Planus"),
    ARCH_RAISED("Arch: Raised (Pes Cavus)"),
   // LOWER_LEG_VERTICAL("Lower Leg is Vertical"),

    // Knee
   // KNEE_IN_LINE("Knee Inline w/ 2nd & 3rd Toes"),
    KNEE_VALGUS("Knee Valgus"),
    KNEE_VARUS("Knee Varus"),
  //  KNEE_STRAIGHT("Knee Straight"),
    KNEE_FLEXED("Knee Flexed"),
    KNEE_HYPEREXTENDED("Knee Hyperextension"),

    // LPHC
   // PELVIS_LEVEL("Pelvis is level"),
    PELVIS_ANTERIOR_TILT("Pelvis Anterior Tilt"),
    PELVIS_POSTERIOR_TILT("Pelvis Posterior Tilt"),
   // HIPS_NEUTRAL("Hips are neutral"),
    HIPS_FLEXED("Hips are flexed"),
    HIPS_EXTENDED("Hips are extended"),
   // LSPINE_NORMAL("L-Spine is normal"),
    LSPINE_EXC_LORDOSIS("L-Spine Excessive Lordosis"),
    LSPINE_RED_LORDOSIS("L-Spine Reduced Lordosis"),
    LSPINE_LATERAL_SHIFT("L-Spine Lateral Shift"),

    // Shoulder and T-spine
   // SHOULDER_LEVEL("Shoulders are level"),
    SHOULDER_ELEVATE("Shoulders are elevated"),
   // SHOULDER_INLINE_HIPS_EARS("Shoulders inline with Hips and Ears"),
    SHOULDER_ROUNDED("Shoulders are rounded"),
  //  TSPINE_NORMAL("T-Spine is normal"),
    TSPINE_EXC_KYPHOSIS("T-Spine Excessive Kyphosis"),

    // Head and C-spine
   // HEAD_NEUTRAL("Head is neutral"),
    HEAD_FORWARD("Head is forward");
    //CSPINE_NORMAL("C-Spine is normal");

    private final String label;

    StaticObservationFinding(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    /*public static List<StaticObservationFinding> getAnteriorObservations() {
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
    }*/
}
