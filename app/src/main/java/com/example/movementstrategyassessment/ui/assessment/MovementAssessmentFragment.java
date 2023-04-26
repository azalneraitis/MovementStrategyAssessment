package com.example.movementstrategyassessment.ui.assessment;

import static com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment.OBS_LABEL;
import static com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment.OBS_MVMT;
import static com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment.OBS_STATIC;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.media.audiofx.DynamicsProcessing;
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
import com.example.movementstrategyassessment.databinding.FragmentMovementAssessmentBinding;
import com.example.movementstrategyassessment.databinding.FragmentStaticPostureBinding;
import com.example.movementstrategyassessment.models.MovementObservationFinding;
import com.example.movementstrategyassessment.models.StageEnum;
import com.example.movementstrategyassessment.models.StaticObservationFinding;
import com.example.movementstrategyassessment.ui.results.ResultListItemFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MovementAssessmentFragment extends Fragment {

    private FragmentMovementAssessmentBinding binding;
    private Map<StageEnum, List<ObservationChoiceFragment>> assessmentToChoicesMap = new HashMap<>();
    private List<ObservationChoiceFragment> currentList = new ArrayList<>();

    private final Set<StaticObservationFinding> prevChoices = new HashSet<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMovementAssessmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        MainActivity act = ((MainActivity) getActivity());
        StageEnum currStage = act.getAssessmentResultsService().getCurrentStage();
        resetForStage(currStage);
    }

    public void resetForStage(StageEnum currStage) {
        final LinearLayout obsList = binding.mvmtLinearObsList;
        final FragmentManager fragManager = getChildFragmentManager();

        clearObsList(fragManager);

        List<MovementObservationFinding> currObs = MovementObservationFinding.getListForStage(currStage);
        for (MovementObservationFinding obs : currObs) {
            ObservationChoiceFragment newObs = new ObservationChoiceFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ObservationChoiceFragment.OBS_MVMT, obs);
            bundle.putString(ObservationChoiceFragment.OBS_LABEL, obs.getLabel());
            bundle.putBoolean(ObservationChoiceFragment.OBS_CHECKED, false);
            newObs.setArguments(bundle);
            currentList.add(newObs);
            fragManager.beginTransaction().setReorderingAllowed(true).add(obsList.getId(), newObs, obs.getLabel()).commit();
        }
    }

    public void clearObsList(FragmentManager fragManager) {
        for (Fragment item : fragManager.getFragments()) {
            if (item instanceof ObservationChoiceFragment) {
                fragManager.beginTransaction().remove(item).commit();
            }
        }
    }

    public List<ObservationChoiceFragment> getPositiveChoices(StageEnum stage) {
        if (stage != StageEnum.STATIC_OBSERVATION && stage != StageEnum.MOBILITY_ASSESSMENT) {
            List<ObservationChoiceFragment> currList = new ArrayList<>();
            List<Fragment> childFrags = getChildFragmentManager().getFragments();
            for (Fragment frag : childFrags) {
                if (frag instanceof ObservationChoiceFragment && ((ObservationChoiceFragment) frag).getSwitchState()) {
                    currList.add((ObservationChoiceFragment) frag);
                }
            }

            assessmentToChoicesMap.put(stage, currList);
        }

        Set<ObservationChoiceFragment> uniqueObs = new HashSet<>();
        for (List<ObservationChoiceFragment> list : assessmentToChoicesMap.values()) {
            uniqueObs.addAll(list);
        }

        List<ObservationChoiceFragment> retList = new ArrayList<>();
        retList.addAll(uniqueObs);
        return retList;
    }
}