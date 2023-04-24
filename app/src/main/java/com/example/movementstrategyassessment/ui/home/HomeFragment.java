package com.example.movementstrategyassessment.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.movementstrategyassessment.MainActivity;
import com.example.movementstrategyassessment.R;
import com.example.movementstrategyassessment.databinding.FragmentHomeBinding;
import com.example.movementstrategyassessment.models.AssessmentResultService;
import com.example.movementstrategyassessment.models.StageEnum;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MainActivity act = ((MainActivity) getActivity());
        refreshHomeStatusDisplay();
        Button newButton = (Button) view.findViewById(R.id.home_button_new_assessment);
        newButton.setOnClickListener(v -> {
            if (act.getAssessmentResultsService().getIsAssessmentStarted()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setMessage("An assessment has already been started. Starting a new assessment will erase the current one. Continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startNewAssessment();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                startNewAssessment();
            }
        });
    }

    public void startNewAssessment()
    {
        MainActivity act = ((MainActivity) getActivity());
        AssessmentResultService assessmentSvc = act.getAssessmentResultsService();
        assessmentSvc.setIsAssessmentStarted(true);
        assessmentSvc.setCurrentStage(StageEnum.STATIC_OBSERVATION);
        refreshHomeStatusDisplay();
    }

    public void refreshHomeStatusDisplay() {
        MainActivity act = ((MainActivity) getActivity());
        binding.textStatus.setText(new StringBuilder().append(getString(R.string.status_display_message)).append(" ")
                .append(act.getAssessmentResultsService().getCurrentStage().getLabel()).toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}