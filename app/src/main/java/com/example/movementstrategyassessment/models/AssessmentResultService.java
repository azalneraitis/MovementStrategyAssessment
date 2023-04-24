package com.example.movementstrategyassessment.models;

import android.app.Service;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.IBinder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AssessmentResultService {
    Set<StaticObservationFinding> staticFindings = new HashSet<>();
    Set<MovementObservationFinding> movementFindings = new HashSet<>();
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
                case SINGLE_LEG_SQUAT:
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
                    break;
                case MOBILITY_ASSESSMENT:
                    setPrevStage(StageEnum.DAVIES_TEST);
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
                setCurrentStage(StageEnum.SINGLE_LEG_SQUAT);
                break;
            case SINGLE_LEG_SQUAT:
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
                break;
            case MOBILITY_ASSESSMENT:
                setPrevStage(this.getCurrentStage());
                setCurrentStage(StageEnum.RESULTS_STAGE);
                break;
        }
    }


    public void handleSaveButtonPushed() {
        if (this.getPrevStage() != StageEnum.UNSTARTED) {
            this.setCurrentStage(this.getPrevStage());
            switch (this.getCurrentStage()) {
                case STATIC_OBSERVATION:
                    setPrevStage(StageEnum.UNSTARTED);
                    break;
                case OVERHEAD_SQUAT_ASSESSMENT:
                    setPrevStage(StageEnum.STATIC_OBSERVATION);
                    break;
                case SINGLE_LEG_SQUAT:
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
                    break;
                case MOBILITY_ASSESSMENT:
                    setPrevStage(StageEnum.DAVIES_TEST);
                    break;
            }
        }
    }
}