package com.example.movementstrategyassessment.models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: Convert to JSON import
public enum MobilityObservationFinding {
    // Foot and ankle
    MTP("First MTP Test"),
    WBL("Weight Bearing Lunge Test"),
    KNEE_FLEX("Knee Flexion Test"),
    KNEE_EXT("Knee Extension"),
    MODIFIED_THOMAS_EXT("Modified Thomas Test - Hip Extension"),
    MODIFIED_THOMAS_ADD("Modified Thomas Test - Hip Adduction"),
    MODIFIED_THOMAS_FLEX("Modified Thomas Test - Knee Flexion"),

    HIP_INT("Hip Internal Rotation"),
    HIP_EXT("Hip External Rotation"),
    LUMBAR_FLEX("Lumbar Flexion"),
    LUMBAR_EXT("Lumbar Extension"),
    HIP_ABD_ROT("Hip Abduction and External Rotation"),
    THOR_EXT("Thoracic Extension"),
    THOR_ROT("Thoracic Rotation"),
    SHOULDER_FLEX("Shoulder Flexion"),
    SHOULDER_EXT("Shoulder Extension"),
    SHOULDER_INT_ROT("Shoulder Internal Rotation"),
    SHOULDER_EXT_ROT("Shoulder External Rotation"),
    PEC_MINOR("Shoulder Retraction/Pectoralis Minor"),
    CERV_FLEX("Cervical Flexion"),
    CERV_EXT("Cervical Extension"),
    CERV_LAT("Cervical Lateral Flexion"),
    CERV_ROT("Cervical Rotation"),
    ELBOW_FLEX("Elbow Flexion"),
    ELBOW_EXT("Elbow Extension"),
    WRIST_FLEX("Wrist Flexion"),
    WRIST_EXT("Wrist Extension");
    /*
    FEET_EXTERNAL_ROTATE("Feet: Externally Rotated/Turned Out"),
    ARCH_FLATTENED("Arch: Flattened (Pes Planus"),
    ARCH_RAISED("Arch: Raised (Pes Cavus)"),
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
    CERVICAL_EXTENSION("Excessive Cervical Extension/Forward Head");*/

    private final String label;

    MobilityObservationFinding(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static List<MobilityObservationFinding> getListForStage(StageEnum stage) {
        List<MobilityObservationFinding> retList = new ArrayList<>();
        switch (stage) {
            case OVERHEAD_SQUAT_ASSESSMENT:
                retList.addAll(getOHSA());
                break;
        }

        return retList;
    }

    private static List<MobilityObservationFinding> getOHSA() {
        return Arrays.asList(MobilityObservationFinding.values());
    }
}
