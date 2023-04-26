package com.example.movementstrategyassessment.models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Convert to JSON import
public enum MovementObservationFinding {
    // Foot and ankle
    FEET_EXTERNAL_ROTATE("Feet: Externally Rotated/Turned Out"),
    ARCH_FLATTENED("Arch: Flattened (Pes Planus"),
    //ARCH_RAISED("Arch: Raised (Pes Cavus)"),
    HEEL_RISE("Heel Rise"),
    KNEE_VALGUS("Knee Valgus"),
    KNEE_VARUS("Knee Varus"),
    KNEE_DOMINANCE("Knee Dominance"),
    ASYMMETRIC_HIP("Asymmetric Weight Shift"),
    ANTERIOR_PELVIC("Excessive Anterior Pelvic Tilt"),
    POST_PELVIC("Excessive Posterior Pelvic Tilt"),
    TRUNK_LEAN("Excessive Forward Trunk Lean"),
    SCAPULAR_ELEV("Scapular Elevation"),
    ARMS_FALL_FWD("Arms Fall Forward"),
    CERVICAL_EXTENSION("Excessive Cervical Extension/Forward Head");

    private final String label;

    MovementObservationFinding(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static List<MovementObservationFinding> getListForStage(StageEnum stage) {
        List<MovementObservationFinding> retList = new ArrayList<>();
        switch (stage) {
            case OVERHEAD_SQUAT_ASSESSMENT:
                retList.addAll(getOHSA());
                break;
        }

        return retList;
    }

    private static List<MovementObservationFinding> getOHSA() {
        return Arrays.asList(MovementObservationFinding.values());
    }
}
