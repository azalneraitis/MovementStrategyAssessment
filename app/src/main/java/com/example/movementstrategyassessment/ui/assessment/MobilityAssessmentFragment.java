package com.example.movementstrategyassessment.ui.assessment;

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

import com.example.movementstrategyassessment.R;
import com.example.movementstrategyassessment.databinding.FragmentMobilityAssessmentBinding;
import com.example.movementstrategyassessment.databinding.FragmentStaticPostureBinding;
import com.example.movementstrategyassessment.models.MobilityObservationFinding;
import com.example.movementstrategyassessment.models.StaticObservationFinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MobilityAssessmentFragment extends Fragment {

    private FragmentMobilityAssessmentBinding binding;
    private final Set<StaticObservationFinding> prevChoices = new HashSet<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMobilityAssessmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        final LinearLayout obsList = binding.mobLinearObsList;
        final FragmentManager fragManager = getChildFragmentManager();

        clearObsList(fragManager);

        for (MobilityObservationFinding obs : MobilityObservationFinding.values()) {
            ObservationChoiceFragment newObs = new ObservationChoiceFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ObservationChoiceFragment.OBS_MOB, obs);
            bundle.putString(ObservationChoiceFragment.OBS_LABEL, obs.getLabel());
            newObs.setArguments(bundle);
            fragManager.beginTransaction().setReorderingAllowed(true).add(obsList.getId(), newObs, obs.getLabel()).commit();
        }
    }

    public List<ObservationChoiceFragment> getPositiveChoices() {
        List<ObservationChoiceFragment> retList = new ArrayList<>();

        List<Fragment> childFrags = getChildFragmentManager().getFragments();
        for (Fragment frag : childFrags) {
            if (frag instanceof ObservationChoiceFragment && ((ObservationChoiceFragment) frag).getSwitchState()) {
                retList.add((ObservationChoiceFragment) frag);
            }
        }

        return retList;
    }

    public void clearObsList(FragmentManager fragManager) {
        for (Fragment item : fragManager.getFragments()) {
            if (item instanceof ObservationChoiceFragment) {
                fragManager.beginTransaction().remove(item).commit();
            }
        }
    }
}