package com.example.movementstrategyassessment.ui.assessment;

import static com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment.OBS_LABEL;
import static com.example.movementstrategyassessment.ui.assessment.ObservationChoiceFragment.OBS_STATIC;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;


import com.example.movementstrategyassessment.R;
import com.example.movementstrategyassessment.databinding.FragmentStaticPostureBinding;
import com.example.movementstrategyassessment.models.StaticObservationFinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticPostureFragment extends Fragment {

    private FragmentStaticPostureBinding binding;
    private final Set<StaticObservationFinding> prevChoices = new HashSet<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStaticPostureBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        final LinearLayout obsList = binding.linearObsList;
        final FragmentManager fragManager = getChildFragmentManager();

        clearObsList(fragManager);
        for (StaticObservationFinding obs : StaticObservationFinding.values()) {
            ObservationChoiceFragment newObs = new ObservationChoiceFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(OBS_STATIC, obs);
            bundle.putString(OBS_LABEL, obs.getLabel());
            if(prevChoices.contains(obs)){
                bundle.putBoolean(ObservationChoiceFragment.OBS_CHECKED, true);
            }
            newObs.setArguments(bundle);
            fragManager.beginTransaction().setReorderingAllowed(true).add(obsList.getId(), newObs, obs.getLabel()).commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
        prevChoices.clear();
        for (Fragment item : fragManager.getFragments()) {
            if (item instanceof ObservationChoiceFragment) {
                if (((ObservationChoiceFragment) item).getSwitchState()) {
                    prevChoices.add(((ObservationChoiceFragment) item).getStaticFinding());
                }
                fragManager.beginTransaction().remove(item).commit();
            }
        }
    }
}