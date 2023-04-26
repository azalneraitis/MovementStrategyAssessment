package com.example.movementstrategyassessment.ui.results;

import static com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment.OBS_LABEL;
import static com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment.OBS_STATIC;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.movementstrategyassessment.MainActivity;
import com.example.movementstrategyassessment.R;
import com.example.movementstrategyassessment.databinding.FragmentResultsBinding;
import com.example.movementstrategyassessment.databinding.FragmentStaticPostureBinding;
import com.example.movementstrategyassessment.models.AssessmentResultService;
import com.example.movementstrategyassessment.models.MobilityObservationFinding;
import com.example.movementstrategyassessment.models.MovementObservationFinding;
import com.example.movementstrategyassessment.models.MuscleGroup;
import com.example.movementstrategyassessment.models.StaticObservationFinding;
import com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ResultsFragment extends Fragment {

    private ResultsViewModel mViewModel;
    private FragmentResultsBinding binding;

    public static ResultsFragment newInstance() {
        return new ResultsFragment();
    }

    public List<ResultListItemFragment> addedFrags = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentResultsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        MainActivity act = ((MainActivity) getActivity());
        AssessmentResultService assessmentResultService = act.getAssessmentResultsService();

        final FragmentManager fragManager = getChildFragmentManager();
        clearItemList(fragManager);

        populateObservationsSection(assessmentResultService, fragManager);
        populateUnderactiveSection(assessmentResultService, fragManager);
        populateOveractiveSection(assessmentResultService, fragManager);
    }

    private void populateObservationsSection(AssessmentResultService assessmentResultService, FragmentManager fragManager) {
        final LinearLayout obsList = binding.resultsObservationLayout;

        Set<StaticObservationFinding> staticFindings = assessmentResultService.getStaticFindings();
        Set<MovementObservationFinding> movementFindings = assessmentResultService.getMovementFindings();
        Set<MobilityObservationFinding> mobFindings = assessmentResultService.getMobFindings();

        for (StaticObservationFinding staticObs : staticFindings) {
            ResultListItemFragment newListItem = new ResultListItemFragment();
            Bundle bundle = new Bundle();

            String itemString = "Static: " + staticObs.getLabel();
            bundle.putString(ResultListItemFragment.RESULT_ITEM, itemString);
            newListItem.setArguments(bundle);
            addedFrags.add(newListItem);
            fragManager.beginTransaction().setReorderingAllowed(true).add(obsList.getId(), newListItem, itemString).commit();
        }

        for (MovementObservationFinding mvmtObs : movementFindings) {
            ResultListItemFragment newListItem = new ResultListItemFragment();
            Bundle bundle = new Bundle();

            String itemString = "Movement: " + mvmtObs.getLabel();
            bundle.putString(ResultListItemFragment.RESULT_ITEM, itemString);
            newListItem.setArguments(bundle);
            addedFrags.add(newListItem);
            fragManager.beginTransaction().setReorderingAllowed(true).add(obsList.getId(), newListItem, itemString).commit();
        }

        for (MobilityObservationFinding mobObs : mobFindings) {
            ResultListItemFragment newListItem = new ResultListItemFragment();
            Bundle bundle = new Bundle();

            String itemString = "Mobility Passed: " + mobObs.getLabel();
            bundle.putString(ResultListItemFragment.RESULT_ITEM, itemString);
            newListItem.setArguments(bundle);
            addedFrags.add(newListItem);
            fragManager.beginTransaction().setReorderingAllowed(true).add(obsList.getId(), newListItem, itemString).commit();
        }
    }

    private void populateUnderactiveSection(AssessmentResultService assessmentResultService, FragmentManager fragManager) {
        final LinearLayout underactiveList = binding.resultsUnderactiveLayout;

        Set<MuscleGroup> underactive = assessmentResultService.generateUnderactiveList();

        for (MuscleGroup muscleGroup : underactive) {
            ResultListItemFragment newListItem = new ResultListItemFragment();
            Bundle bundle = new Bundle();

            bundle.putString(ResultListItemFragment.RESULT_ITEM, muscleGroup.getLabel());
            newListItem.setArguments(bundle);
            addedFrags.add(newListItem);
            fragManager.beginTransaction().setReorderingAllowed(true).add(underactiveList.getId(), newListItem, muscleGroup.getLabel()).commit();
        }
    }

    private void populateOveractiveSection(AssessmentResultService assessmentResultService, FragmentManager fragManager) {
        final LinearLayout overactiveList = binding.resultsOveractiveLayout;
        Set<MuscleGroup> overactive = assessmentResultService.generateOveractiveList();

        for (MuscleGroup muscleGroup : overactive) {
            ResultListItemFragment newListItem = new ResultListItemFragment();
            Bundle bundle = new Bundle();

            bundle.putString(ResultListItemFragment.RESULT_ITEM, muscleGroup.getLabel());
            newListItem.setArguments(bundle);
            addedFrags.add(newListItem);
            fragManager.beginTransaction().setReorderingAllowed(true).add(overactiveList.getId(), newListItem, muscleGroup.getLabel()).commit();
        }
    }

    public void clearItemList(FragmentManager fragManager) {
        for (Fragment item : fragManager.getFragments()) {
            if (item instanceof ResultListItemFragment) {
                fragManager.beginTransaction().remove(item).commit();
            }
        }
    }
}