package com.example.movementstrategyassessment.models;

public enum StageEnum {
    UNSTARTED("Not Started"),
    STATIC_OBSERVATION("Static Posture Assessment"),
    OVERHEAD_SQUAT_ASSESSMENT("Overhead Squat Assessment"),
    /*HEELS_RAISED_OHSA("Heels-Raised OHSA"),
    HANDS_HIPS_OHSA("Hands-On-Hips OHSA"),*/
    /*SINGLE_LEG_SQUAT("Single-Leg/Split Squat Assessment"),
    LOADED_SQUAT("Loaded Squat Assessment"),
    LOADED_PUSH("Loaded Push Assessment"),
    LOADED_PULL("Loaded Pull Assessment"),
    OVERHEAD_PRESS("Standing Overhead Press Assessment"),
    GAIT_ASSESSMENT("Gait Assessment"),
    DEPTH_JUMP("Depth Jump Assessment"),
    DAVIES_TEST("Davies Test"),*/
    MOBILITY_ASSESSMENT("Mobility Assessment"),
    RESULTS_STAGE("Results");

    private final String label;

    StageEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
