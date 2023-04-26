package com.example.movementstrategyassessment.models;

import android.app.Service;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.IBinder;

import com.example.movementstrategyassessment.ui.assessment.MobilityAssessmentFragment;
import com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.microedition.khronos.opengles.GL;

public class AssessmentResultService {
    Set<StaticObservationFinding> staticFindings = new HashSet<>();
    Set<MovementObservationFinding> movementFindings = new HashSet<>();
    Set<MobilityObservationFinding> mobFindings = new HashSet<>();

    Set<MuscleGroup> overactiveMuscles = new HashSet<>();
    Set<MuscleGroup> underactiveMuscles = new HashSet<>();
    private boolean isAssessmentStarted = false;
    private StageEnum currentStage = StageEnum.UNSTARTED;
    private StageEnum prevStage = StageEnum.UNSTARTED;

    public AssessmentResultService() {
    }

    public void addStaticFinding(Set<StaticObservationFinding> findings) {
        this.staticFindings.addAll(findings);
    }

    public void removeStaticFinding(Set<StaticObservationFinding> findings) {
        this.staticFindings.removeAll(findings);
    }

    public void addMovementFinding(Set<MovementObservationFinding> findings) {
        this.movementFindings.addAll(findings);
    }

    public void removeMovementFinding(Set<MovementObservationFinding> findings) {
        this.movementFindings.removeAll(findings);
        this.evaluateFindings();
    }

    private void evaluateFindings() {

    }

    public void startNewAssessment() {
        this.setIsAssessmentStarted(true);
        setCurrentStage(StageEnum.STATIC_OBSERVATION);
    }

    public boolean getIsAssessmentStarted() {
        return isAssessmentStarted;
    }

    public void setIsAssessmentStarted(boolean assessmentStarted) {
        this.isAssessmentStarted = assessmentStarted;
    }

    public StageEnum getCurrentStage() {
        return this.currentStage;
    }

    public void setCurrentStage(StageEnum stage) {
        this.currentStage = stage;
    }

    public StageEnum getPrevStage() {
        return prevStage;
    }

    public void setPrevStage(StageEnum prevStage) {
        this.prevStage = prevStage;
    }

    public void handleBackButtonPushed() {
        if (this.getPrevStage() != StageEnum.UNSTARTED) {
            this.setCurrentStage(this.getPrevStage());
            switch (this.getCurrentStage()) {
                case STATIC_OBSERVATION:
                    setPrevStage(StageEnum.UNSTARTED);
                    break;
                case OVERHEAD_SQUAT_ASSESSMENT:
                    setPrevStage(StageEnum.STATIC_OBSERVATION);
                    break;
                /*case SINGLE_LEG_SQUAT:
                    setPrevStage(StageEnum.OVERHEAD_SQUAT_ASSESSMENT);
                    break;
                case LOADED_SQUAT:
                    setPrevStage(StageEnum.SINGLE_LEG_SQUAT);
                    break;
                case LOADED_PUSH:
                    setPrevStage(StageEnum.LOADED_SQUAT);
                    break;
                case LOADED_PULL:
                    setPrevStage(StageEnum.LOADED_PUSH);
                    break;
                case OVERHEAD_PRESS:
                    setPrevStage(StageEnum.LOADED_PULL);
                    break;
                case GAIT_ASSESSMENT:
                    setPrevStage(StageEnum.OVERHEAD_PRESS);
                    break;
                case DEPTH_JUMP:
                    setPrevStage(StageEnum.GAIT_ASSESSMENT);
                    break;
                case DAVIES_TEST:
                    setPrevStage(StageEnum.DEPTH_JUMP);
                    break;*/
                case MOBILITY_ASSESSMENT:
                    setPrevStage(StageEnum.OVERHEAD_SQUAT_ASSESSMENT);
                    break;
            }
        }
    }

    public void handleNextButtonPushed() {
        switch (this.getCurrentStage()) {
            case STATIC_OBSERVATION:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.OVERHEAD_SQUAT_ASSESSMENT);
                break;
            case OVERHEAD_SQUAT_ASSESSMENT:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.MOBILITY_ASSESSMENT);
                break;
            /*case SINGLE_LEG_SQUAT:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.LOADED_SQUAT);
                break;
            case LOADED_SQUAT:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.LOADED_PUSH);
                break;
            case LOADED_PUSH:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.LOADED_PULL);
                break;
            case LOADED_PULL:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.OVERHEAD_PRESS);
                break;
            case OVERHEAD_PRESS:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.GAIT_ASSESSMENT);
                break;
            case GAIT_ASSESSMENT:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.DEPTH_JUMP);
                break;
            case DEPTH_JUMP:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.DAVIES_TEST);
                break;
            case DAVIES_TEST:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.MOBILITY_ASSESSMENT);
                break;*/
            case MOBILITY_ASSESSMENT:
               /* setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.RESULTS_STAGE);*/
                break;
        }
    }

    public void handleSaveButtonPushed(List<ObservationChoiceFragment> observations) {
        staticFindings.clear();
        movementFindings.clear();
        mobFindings.clear();
        for (ObservationChoiceFragment obs : observations) {
            if (obs.getStaticFinding() != null) {
                staticFindings.add(obs.getStaticFinding());
            } else if (obs.getMovementFinding() != null) {
                movementFindings.add(obs.getMovementFinding());
            } else if (obs.getMobFinding() != null) {
                mobFindings.add(obs.getMobFinding());
            }
        }
    }

    public Set<StaticObservationFinding> getStaticFindings() {
        return staticFindings;
    }

    public Set<MovementObservationFinding> getMovementFindings() {
        return movementFindings;
    }

    public Set<MobilityObservationFinding> getMobFindings() {
        return mobFindings;
    }

    public Set<MuscleGroup> generateUnderactiveList() {
        Set<MuscleGroup> ret = new HashSet<>();
        for (StaticObservationFinding statFind : staticFindings) {
            List<MuscleGroup> groups = getUnderactiveMuscles(statFind);
            ret.addAll(groups);
        }
        for (MovementObservationFinding mvmtFind : movementFindings) {
            List<MuscleGroup> groups = getUnderactiveMuscles(mvmtFind);
            ret.addAll(groups);
        }

        return ret;
    }

    public Set<MuscleGroup> generateOveractiveList() {
        Set<MuscleGroup> ret = new HashSet<>();
        for (StaticObservationFinding statFind : staticFindings) {
            List<MuscleGroup> groups = getOveractiveMuscles(statFind);
            ret.addAll(groups);
        }

        for (MovementObservationFinding mvmtFind : movementFindings) {
            List<MuscleGroup> groups = getOveractiveMuscles(mvmtFind);
            ret.addAll(groups);
        }

        for (MobilityObservationFinding mobFind : mobFindings) {
            List<MuscleGroup> groups = getOveractiveMusclesToRemove(mobFind);
            ret.removeAll(groups);
        }

        return ret;
    }

    private List<MuscleGroup> getUnderactiveMuscles(StaticObservationFinding finding) {
        List<MuscleGroup> ret = new ArrayList<>();
        switch (finding) {
            case FEET_EXTERNAL_ROTATE:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GASTROC_MED);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.HAMSTRING_MED);
                ret.add(MuscleGroup.POST_TIB);
                break;
            case ARCH_FLATTENED:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.INT_FOOT);
                ret.add(MuscleGroup.POST_TIB);
                ret.add(MuscleGroup.GASTROC_MED);
                break;
            case ARCH_RAISED:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                break;
            case KNEE_VALGUS:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.HAMSTRING_MED);
                ret.add(MuscleGroup.POST_TIB);
                ret.add(MuscleGroup.VMO);
                break;
            case KNEE_VARUS:
                ret.add(MuscleGroup.ADD_MAG);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.HAMSTRING_MED);
                break;
            case KNEE_FLEXED:
                ret.add(MuscleGroup.CORE_STAB);
                ret.add(MuscleGroup.GLUTE_MAX);
                break;
            case KNEE_HYPEREXTENDED:
                ret.add(MuscleGroup.PSOAS);
                break;
            case PELVIS_ANTERIOR_TILT:
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.HAMSTRINGS);
                ret.add(MuscleGroup.CORE_LOCAL);
                ret.add(MuscleGroup.RECT_ABD);
                break;
            case PELVIS_POSTERIOR_TILT:
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.CORE_LOCAL);
                ret.add(MuscleGroup.PSOAS);
                ret.add(MuscleGroup.RECT_FEM);
                ret.add(MuscleGroup.SPINE_EXT);
                ret.add(MuscleGroup.TFL);
                break;
            case HIPS_FLEXED:
            case LSPINE_EXC_LORDOSIS:
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.HAMSTRINGS);
                break;
            case HIPS_EXTENDED:
                ret.add(MuscleGroup.SPINE_EXT);
                ret.add(MuscleGroup.PSOAS);
                break;
            case LSPINE_LATERAL_SHIFT:
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.CORE_STAB);
                ret.add(MuscleGroup.ADD_COMP);

                break;
            case LSPINE_RED_LORDOSIS:
                ret.add(MuscleGroup.SPINE_EXT);
                ret.add(MuscleGroup.PSOAS);
                ret.add(MuscleGroup.OBLIQUE_EXT);
                break;
            case SHOULDER_ELEVATE:
                ret.add(MuscleGroup.TRAP_LOWER);
                ret.add(MuscleGroup.SERRATUS_ANT);
                break;
            case SHOULDER_ROUNDED:
                ret.add(MuscleGroup.TRAP_LOWER);
                ret.add(MuscleGroup.TRAP_MID);
                break;
            case TSPINE_EXC_KYPHOSIS:
            case HEAD_FORWARD:
                ret.add(MuscleGroup.TRAP_LOWER);
                ret.add(MuscleGroup.TRAP_MID);
                ret.add(MuscleGroup.CERVICAL_FLEX_DEEP);
                break;
        }

        return ret;
    }

    private List<MuscleGroup> getUnderactiveMuscles(MovementObservationFinding finding) {
        List<MuscleGroup> ret = new ArrayList<>();
        switch (finding) {
            case FEET_EXTERNAL_ROTATE:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GASTROC_MED);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.HAMSTRING_MED);
                ret.add(MuscleGroup.POST_TIB);
                break;
            case ARCH_FLATTENED:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GASTROC_MED);
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.INT_FOOT);
                ret.add(MuscleGroup.POST_TIB);
                break;
            case HEEL_RISE:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GLUTE_MAX);
                break;
            case KNEE_VALGUS:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.HAMSTRING_MED);
                ret.add(MuscleGroup.POST_TIB);
                ret.add(MuscleGroup.VMO);
                break;
            case KNEE_VARUS:
                ret.add(MuscleGroup.ADD_COMP);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.HAMSTRING_MED);
                break;
            case KNEE_DOMINANCE:
                ret.add(MuscleGroup.CORE_STAB);
                ret.add(MuscleGroup.GLUTE_MAX);
                break;
            case ASYMMETRIC_HIP:
                ret.add(MuscleGroup.CORE_STAB);
                ret.add(MuscleGroup.GLUTE_MED);
                ret.add(MuscleGroup.ADD_COMP);
                break;
            case ANTERIOR_PELVIC:
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.HAMSTRINGS);
                ret.add(MuscleGroup.CORE_LOCAL);
                ret.add(MuscleGroup.RECT_ABD);
                break;
            case POST_PELVIC:
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.CORE_LOCAL);
                ret.add(MuscleGroup.PSOAS);
                ret.add(MuscleGroup.RECT_FEM);
                ret.add(MuscleGroup.SPINE_EXT);
                ret.add(MuscleGroup.TFL);
                break;
            case TRUNK_LEAN:
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.GLUTE_MAX);
                ret.add(MuscleGroup.HAMSTRINGS);
                ret.add(MuscleGroup.CORE_LOCAL);
                ret.add(MuscleGroup.SPINE_EXT);
                break;
            case SCAPULAR_ELEV:
                ret.add(MuscleGroup.TRAP_LOWER);
                ret.add(MuscleGroup.SERRATUS_ANT);
                break;
            case ARMS_FALL_FWD:
                ret.add(MuscleGroup.INFRASPINATUS);
                ret.add(MuscleGroup.TRAP_LOWER);
                ret.add(MuscleGroup.TRAP_MID);
                ret.add(MuscleGroup.DELT_POST);
                ret.add(MuscleGroup.RHOMBOIDS);
                ret.add(MuscleGroup.TERES_MIN);
                break;
            case CERVICAL_EXTENSION:
                ret.add(MuscleGroup.CERVICAL_FLEX_DEEP);
                ret.add(MuscleGroup.TRAP_LOWER);
                ret.add(MuscleGroup.TRAP_MID);
                ret.add(MuscleGroup.RHOMBOIDS);
                break;
        }

        return ret;
    }

    private List<MuscleGroup> getOveractiveMuscles(StaticObservationFinding finding) {
        List<MuscleGroup> ret = new ArrayList<>();
        switch (finding) {
            case FEET_EXTERNAL_ROTATE:
                ret.add(MuscleGroup.BICEP_FEM_SHORT);
                ret.add(MuscleGroup.GASTROC_LAT);
                ret.add(MuscleGroup.SOL);
                break;
            case ARCH_FLATTENED:
                ret.add(MuscleGroup.FIB_COMP);
                ret.add(MuscleGroup.GASTROC_LAT);
                ret.add(MuscleGroup.TFL);
                break;
            case KNEE_VALGUS:
                ret.add(MuscleGroup.ADD_MAG);
                ret.add(MuscleGroup.BICEP_FEM_SHORT);
                ret.add(MuscleGroup.GASTROC);
                ret.add(MuscleGroup.SOL);
                ret.add(MuscleGroup.TFL);
                ret.add(MuscleGroup.VAST_LAT);
                break;
            case KNEE_VARUS:
                ret.add(MuscleGroup.ADD_MAG_POST);
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.BICEP_FEM_LONG);
                ret.add(MuscleGroup.PIRIFORMIS);
                ret.add(MuscleGroup.POST_TIB);
                ret.add(MuscleGroup.TFL);
                break;
            case KNEE_FLEXED:
                ret.add(MuscleGroup.GASTROC);
                ret.add(MuscleGroup.SOL);
                break;
            case HIPS_EXTENDED:
            case PELVIS_ANTERIOR_TILT:
            case LSPINE_EXC_LORDOSIS:

                ret.add(MuscleGroup.ADD_COMP_ANT);
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.PSOAS);
                ret.add(MuscleGroup.RECT_FEM);
                ret.add(MuscleGroup.SPINE_EXT);
                ret.add(MuscleGroup.TFL);
                break;
            case HIPS_FLEXED:
            case PELVIS_POSTERIOR_TILT:
            case LSPINE_RED_LORDOSIS:
                ret.add(MuscleGroup.ADD_MAG);
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.HAMSTRINGS);
                ret.add(MuscleGroup.PIRIFORMIS);
                ret.add(MuscleGroup.RECT_ABD);
                break;

            case LSPINE_LATERAL_SHIFT:
                ret.add(MuscleGroup.ADD_COMP);
                ret.add(MuscleGroup.TFL);
                ret.add(MuscleGroup.BICEP_FEM_LONG);
                ret.add(MuscleGroup.GASTROC);
                ret.add(MuscleGroup.SOL);
                ret.add(MuscleGroup.PIRIFORMIS);
                break;
            case SHOULDER_ELEVATE:
                ret.add(MuscleGroup.LEV_SCAP);
                ret.add(MuscleGroup.PEC_MIN);
                ret.add(MuscleGroup.TRAP_UPPER);
                break;
            case SHOULDER_ROUNDED:
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.PEC_MIN);
                ret.add(MuscleGroup.TRAP_UPPER);
                break;
            case TSPINE_EXC_KYPHOSIS:
            case HEAD_FORWARD:
                ret.add(MuscleGroup.CERVICAL_FLEX_DEEP);
                ret.add(MuscleGroup.LEV_SCAP);
                ret.add(MuscleGroup.STERNO);
                ret.add(MuscleGroup.TRAP_UPPER);
                break;
        }

        return ret;
    }

    private List<MuscleGroup> getOveractiveMuscles(MovementObservationFinding finding) {
        List<MuscleGroup> ret = new ArrayList<>();
        switch (finding) {
            case FEET_EXTERNAL_ROTATE:
                ret.add(MuscleGroup.BICEP_FEM_SHORT);
                ret.add(MuscleGroup.GASTROC_LAT);
                ret.add(MuscleGroup.SOL);
                break;
            case ARCH_FLATTENED:
                ret.add(MuscleGroup.FIB_COMP);
                ret.add(MuscleGroup.GASTROC_LAT);
                ret.add(MuscleGroup.TFL);
                break;
            case HEEL_RISE:
                ret.add(MuscleGroup.QUAD);
                ret.add(MuscleGroup.SOL);
                break;
            case KNEE_VALGUS:
                ret.add(MuscleGroup.ADD_COMP);
                ret.add(MuscleGroup.BICEP_FEM_SHORT);
                ret.add(MuscleGroup.GASTROC);
                ret.add(MuscleGroup.SOL);
                ret.add(MuscleGroup.TFL);
                ret.add(MuscleGroup.VAST_LAT);
                break;
            case KNEE_VARUS:
                ret.add(MuscleGroup.ADD_MAG_POST);
                ret.add(MuscleGroup.ANTERIOR_TIB);
                ret.add(MuscleGroup.BICEP_FEM_LONG);
                ret.add(MuscleGroup.PIRIFORMIS);
                ret.add(MuscleGroup.POST_TIB);
                ret.add(MuscleGroup.TFL);
                break;
            case KNEE_DOMINANCE:
                ret.add(MuscleGroup.ADD_MAG);
                ret.add(MuscleGroup.PIRIFORMIS);
                ret.add(MuscleGroup.QUAD);
                ret.add(MuscleGroup.SOL);
                break;
            case ASYMMETRIC_HIP:
                ret.add(MuscleGroup.ADD_COMP);
                ret.add(MuscleGroup.TFL);
                ret.add(MuscleGroup.RECT_FEM);
                ret.add(MuscleGroup.GASTROC);
                ret.add(MuscleGroup.SOL);
                ret.add(MuscleGroup.PIRIFORMIS);
                break;
            case ANTERIOR_PELVIC:
                ret.add(MuscleGroup.ADD_COMP_ANT);
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.PSOAS);
                ret.add(MuscleGroup.RECT_FEM);
                ret.add(MuscleGroup.SPINE_EXT);
                ret.add(MuscleGroup.TFL);
                break;
            case POST_PELVIC:
                ret.add(MuscleGroup.ADD_MAG);
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.HAMSTRINGS);
                ret.add(MuscleGroup.PIRIFORMIS);
                ret.add(MuscleGroup.RECT_ABD);
                break;
            case TRUNK_LEAN:
                ret.add(MuscleGroup.ADD_COMP);
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.GASTROC);
                ret.add(MuscleGroup.PSOAS);
                ret.add(MuscleGroup.RECT_ABD);
                ret.add(MuscleGroup.RECT_FEM);
                ret.add(MuscleGroup.SOL);
                ret.add(MuscleGroup.TFL);
                break;
            case SCAPULAR_ELEV:
                ret.add(MuscleGroup.LEV_SCAP);
                ret.add(MuscleGroup.PEC_MIN);
                ret.add(MuscleGroup.TRAP_UPPER);
                break;
            case ARMS_FALL_FWD:
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.PEC_MIN);
                ret.add(MuscleGroup.PEC_MAJ);
                ret.add(MuscleGroup.TERES_MAJ);
                break;
            case CERVICAL_EXTENSION:
                ret.add(MuscleGroup.CERVICAL_EXT);
                ret.add(MuscleGroup.LEV_SCAP);
                ret.add(MuscleGroup.STERNO);
                ret.add(MuscleGroup.TRAP_UPPER);
                break;
        }

        return ret;
    }

    public List<MuscleGroup> getOveractiveMusclesToRemove(MobilityObservationFinding finding) {
        List<MuscleGroup> ret = new ArrayList<>();
        switch (finding) {
            case MTP:
                ret.add(MuscleGroup.INT_FOOT);
                break;
            case WBL:
                ret.add(MuscleGroup.GASTROC);
                ret.add(MuscleGroup.SOL);
                break;
            case KNEE_EXT:
                ret.add(MuscleGroup.HAMSTRINGS);
                break;
            case KNEE_FLEX:
                ret.add(MuscleGroup.QUAD);
                break;
            case MODIFIED_THOMAS_ADD:
                ret.add(MuscleGroup.TFL);
                break;
            case MODIFIED_THOMAS_EXT:
                ret.add(MuscleGroup.PSOAS);
                ret.add(MuscleGroup.RECT_FEM);
                break;
            case MODIFIED_THOMAS_FLEX:
                ret.add(MuscleGroup.RECT_FEM);
                break;
            case HIP_INT:
                ret.add(MuscleGroup.PIRIFORMIS);
                ret.add(MuscleGroup.QUAD);
                ret.add(MuscleGroup.GLUTE_MAX);
                break;
            case HIP_EXT:
            case HIP_ABD_ROT:
                ret.add(MuscleGroup.ADD_COMP);
                break;
            case LUMBAR_FLEX:
                ret.add(MuscleGroup.SPINE_EXT);
                break;
            case LUMBAR_EXT:
                ret.add(MuscleGroup.RECT_ABD);
                ret.add(MuscleGroup.OBLIQUE_EXT);
                break;
            case THOR_EXT:
                ret.add(MuscleGroup.RECT_ABD);
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.OBLIQUE_INT);
                break;
            case THOR_ROT:
                ret.add(MuscleGroup.RECT_ABD);
                ret.add(MuscleGroup.OBLIQUE_INT);
                ret.add(MuscleGroup.OBLIQUE_EXT);
                ret.add(MuscleGroup.SPINE_EXT);
                break;
            case SHOULDER_FLEX:
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.TERES_MAJ);
                ret.add(MuscleGroup.PEC_MAJ);
                break;
            case SHOULDER_EXT:
                ret.add(MuscleGroup.DELT_ANT);
                ret.add(MuscleGroup.PEC_MAJ);
                ret.add(MuscleGroup.BICEPS_BRACH);
                break;
            case SHOULDER_INT_ROT:
                ret.add(MuscleGroup.TERES_MIN);
                ret.add(MuscleGroup.INFRASPINATUS);
                break;
            case SHOULDER_EXT_ROT:
                ret.add(MuscleGroup.TERES_MAJ);
                ret.add(MuscleGroup.LATS);
                ret.add(MuscleGroup.PEC_MAJ);
                break;
            case PEC_MINOR:
                ret.add(MuscleGroup.PEC_MIN);
                break;
            case CERV_FLEX:
                ret.add(MuscleGroup.ERECTOR_SPINAE);
                ret.add(MuscleGroup.CERVICAL_EXT);
                ret.add(MuscleGroup.TRAP_UPPER);
                break;
            case CERV_EXT:
                ret.add(MuscleGroup.STERNO);
                ret.add(MuscleGroup.CERVICAL_FLEX_DEEP);
                break;
            case CERV_LAT:
                ret.add(MuscleGroup.STERNO);
                ret.add(MuscleGroup.SCALENES);
                ret.add(MuscleGroup.ERECTOR_SPINAE);
                break;
            case CERV_ROT:
                ret.add(MuscleGroup.STERNO);
                ret.add(MuscleGroup.SCALENES);
                break;
            case ELBOW_FLEX:
                ret.add(MuscleGroup.TRICEPS);
                break;
            case ELBOW_EXT:
                ret.add(MuscleGroup.BICEPS_BRACH);
                ret.add(MuscleGroup.BRACHIALIS);
                ret.add(MuscleGroup.BRACHIORADIALIS);
                break;
            case WRIST_FLEX:
                ret.add(MuscleGroup.WRIST_EXT);
                break;
            case WRIST_EXT:
                ret.add(MuscleGroup.WRIST_FLEX);
                break;
        }

        return ret;
    }
}