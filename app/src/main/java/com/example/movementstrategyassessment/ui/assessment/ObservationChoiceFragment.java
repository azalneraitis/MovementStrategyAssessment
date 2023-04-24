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

public class ObservationChoiceFragment extends Fragment {

    public static ObservationChoiceFragment newInstance() {
        return new ObservationChoiceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_observation_choice, container, false);
    }
}