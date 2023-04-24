package com.example.movementstrategyassessment.ui.assessment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.movementstrategyassessment.databinding.FragmentAssessmentBinding;
import com.example.movementstrategyassessment.databinding.FragmentStaticPostureBinding;

public class StaticPostureFragment extends Fragment {

    private FragmentStaticPostureBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AssessmentViewModel assessmentViewModel =
                new ViewModelProvider(this).get(AssessmentViewModel.class);

        binding = FragmentStaticPostureBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.topHeaderText;
        assessmentViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}