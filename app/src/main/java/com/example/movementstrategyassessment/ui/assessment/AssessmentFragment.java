package com.example.movementstrategyassessment.ui.assessment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.movementstrategyassessment.MainActivity;
import com.example.movementstrategyassessment.R;
import com.example.movementstrategyassessment.databinding.FragmentAssessmentBinding;
import com.example.movementstrategyassessment.models.AssessmentResultService;
import com.example.movementstrategyassessment.models.StageEnum;

public class AssessmentFragment extends Fragment {

    private FragmentAssessmentBinding binding;
    private StaticPostureFragment staticFragment;
    private MovementAssessmentFragment mvmtFragment;
    private MobilityAssessmentFragment mobFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AssessmentViewModel assessmentViewModel =
                new ViewModelProvider(this).get(AssessmentViewModel.class);

        binding = FragmentAssessmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FragmentManager childFrags = getChildFragmentManager();
        staticFragment = (StaticPostureFragment) childFrags.findFragmentById(R.id.static_posture_fragment);
        mvmtFragment= (MovementAssessmentFragment) childFrags.findFragmentById(R.id.movement_assessment_fragment);
        mobFragment= (MobilityAssessmentFragment) childFrags.findFragmentById(R.id.mobility_assessment_fragment);

        configureAssessmentOrderButtons(view);
        refreshStageView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void refreshStageView(View view){
        MainActivity act = (MainActivity) getActivity();
        StageEnum currStage = act.getAssessmentResultsService().getCurrentStage();
        switch (currStage) {
            case UNSTARTED:
                binding.layoutUnstartedAssessment.setVisibility(View.VISIBLE);
                binding.layoutAssessmentNavigationFooter.setVisibility(View.GONE);
                binding.layoutMobilityFragment.setVisibility(View.GONE);
                binding.layoutMovementFragment.setVisibility(View.GONE);
                binding.layoutStaticFragment.setVisibility(View.GONE);
                setupStartButtonAndText(view);
                break;
            case STATIC_OBSERVATION:
                binding.layoutUnstartedAssessment.setVisibility(View.GONE);
                binding.layoutAssessmentNavigationFooter.setVisibility(View.VISIBLE);
                binding.layoutMobilityFragment.setVisibility(View.GONE);
                binding.layoutMovementFragment.setVisibility(View.GONE);
                binding.layoutStaticFragment.setVisibility(View.VISIBLE);
                break;
            case OVERHEAD_SQUAT_ASSESSMENT:
            /*case HEELS_RAISED_OHSA:
            case HANDS_HIPS_OHSA:*/
            case SINGLE_LEG_SQUAT:
            case LOADED_SQUAT:
            case LOADED_PUSH:
            case LOADED_PULL:
            case OVERHEAD_PRESS:
            case GAIT_ASSESSMENT:
            case DEPTH_JUMP:
            case DAVIES_TEST:
                binding.layoutUnstartedAssessment.setVisibility(View.GONE);
                binding.layoutAssessmentNavigationFooter.setVisibility(View.VISIBLE);
                binding.layoutMobilityFragment.setVisibility(View.GONE);
                binding.layoutMovementFragment.setVisibility(View.VISIBLE);
                binding.layoutStaticFragment.setVisibility(View.GONE);
                break;
            case MOBILITY_ASSESSMENT:
                binding.layoutUnstartedAssessment.setVisibility(View.GONE);
                binding.layoutAssessmentNavigationFooter.setVisibility(View.VISIBLE);
                binding.layoutMobilityFragment.setVisibility(View.VISIBLE);
                binding.layoutMovementFragment.setVisibility(View.GONE);
                binding.layoutStaticFragment.setVisibility(View.GONE);
                break;
            case RESULTS_STAGE:
                break;
        }
    }
    private void setupStartButtonAndText(View view) {
        MainActivity act = (MainActivity) getActivity();
        binding.assessmentTextStatus.setText(new StringBuilder().append(getString(R.string.status_display_message)).append(" ")
                .append(act.getAssessmentResultsService().getCurrentStage().getLabel()).toString());

        Button newButton = (Button) view.findViewById(R.id.button_new_assessment);
        newButton.setOnClickListener(v -> {
            AssessmentResultService assessmentSvc = act.getAssessmentResultsService();
            assessmentSvc.setIsAssessmentStarted(true);
            assessmentSvc.setCurrentStage(StageEnum.STATIC_OBSERVATION);
            refreshStageView(v);
        });
    }

    private void configureAssessmentOrderButtons(View view)
    {
        MainActivity act = ((MainActivity) getActivity());

        Button backButton = (Button) view.findViewById(R.id.assessment_back);
        Button saveButton = (Button) view.findViewById(R.id.assessment_save);
        Button nextButton = (Button) view.findViewById(R.id.assessment_next);

        backButton.setOnClickListener(v -> {
            AssessmentResultService assessmentSvc = act.getAssessmentResultsService();
            assessmentSvc.handleBackButtonPushed();
            refreshStageView(view);
        });

        saveButton.setOnClickListener(v -> {
            AssessmentResultService assessmentSvc = act.getAssessmentResultsService();
            assessmentSvc.handleSaveButtonPushed();
            refreshStageView(view);
        });

        nextButton.setOnClickListener(v -> {
            AssessmentResultService assessmentSvc = act.getAssessmentResultsService();
            assessmentSvc.handleNextButtonPushed();
            refreshStageView(view);
        });
    }
}