package com.example.movementstrategyassessment.models;

public enum MuscleGroup {
    ADD_COMP("Adductor Complex"),
    ADD_COMP_ANT("Adductor Complex (Anterior Fibers)"),
    ADD_MAG("Adductor Magnus"),
    ADD_MAG_POST("Adductor Magnus (Posterior Fibers)"),
    ANTERIOR_TIB("Anterior Tibialis"),
    BRACHIALIS("Brachialis"),
    BRACHIORADIALIS("Brachioradialis"),
    BICEPS_BRACH("Biceps Brachii"),
    BICEP_FEM_LONG("Biceps Femoris (Long Head)"),
    BICEP_FEM_SHORT("Biceps Femoris (Short Head)"),
    CERVICAL_EXT("Cervical Extensors (Suboccipital)"),
    CERVICAL_FLEX_DEEP("Deep Cervical Flexors"),
    CORE_LOCAL("Local Core Stabilizers"),
    CORE_STAB("Core Stabilizers"),
    DELT_ANT("Anterior Deltoid"),
    DELT_POST("Posterior Deltoid"),
    ERECTOR_SPINAE("Erector Spinae"),
    FIB_COMP("Fibularis (Peroneal) Complex"),
    GASTROC("Gastrocnemius"),
    GASTROC_LAT("Gastrocnemius (Lateral)"),
    GASTROC_MED("Gastrocnemius (Medial)"),
    GLUTE_MAX("Gluteus Maximus"),
    GLUTE_MED("Gluteus Medius"),
    HAMSTRINGS("Hamstrings Complex"),
    HAMSTRING_LAT("Hamstrings Complex (Lateral)"),
    HAMSTRING_MED("Hamstrings Complex (Medial)"),
    INFRASPINATUS("Infraspinatus"),
    INT_FOOT("Intrinsic Foot Muscles"),
    LATS("Latissimus Dorsi"),
    LEV_SCAP("Levator Scapulae"),
    OBLIQUE_EXT("External Obliques"),
    OBLIQUE_INT("Internal Obliques"),
    PEC_MAJ("Pectoralis Major"),
    PEC_MIN("Pectoralis Minor"),
    PIRIFORMIS("Piriformis"),
    POST_TIB("Posterior Tibialis"),
    PRONATOR_TERES("Pronator Teres"),
    PSOAS("Psoas"),
    QUAD("Quadriceps Complex"),
    RECT_ABD("Rectus Abdominis"),
    RECT_FEM("Rectus Femoris"),
    RHOMBOIDS("Rhomboids"),
    SCALENES("Scalenes"),
    SERRATUS_ANT("Serratus Anterior"),
    SOL("Soleus"),
    SPINE_EXT("Spinal Extensor Complex"),
    STERNO("Sternocleidomastoid"),
    TERES_MAJ("Teres Major"),
    TERES_MIN("Teres Minor"),
    TFL("Tensor Fascia Latae"),
    TRAP_LOWER("Lower Trapezius"),
    TRAP_MID("Middle Trapezius"),
    TRAP_UPPER("Upper Trapezius"),
    TRICEPS("Triceps Group"),
    VAST_LAT("Vastus Lateralis"),
    VMO("Vastus Medialis Oblique (VMO)"),
    WRIST_EXT("Wrist Extensors"),
    WRIST_FLEX("Wrist Flexors");

    private final String label;

    MuscleGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
