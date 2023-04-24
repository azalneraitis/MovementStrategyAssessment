package com.example.movementstrategyassessment.ui.assessment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movementstrategyassessment.R;

public class MobilityAssessmentFragment extends Fragment {

    private AssessmentViewModel mViewModel;

    public static MobilityAssessmentFragment newInstance() {
        return new MobilityAssessmentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mobility_assessment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        // TODO: Use the ViewModel
    }

}