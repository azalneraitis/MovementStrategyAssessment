package com.example.movementstrategyassessment.ui.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.movementstrategyassessment.R;
import com.example.movementstrategyassessment.databinding.FragmentObservationChoiceBinding;
import com.example.movementstrategyassessment.databinding.FragmentResultListItemBinding;
import com.example.movementstrategyassessment.databinding.FragmentResultsBinding;
import com.example.movementstrategyassessment.models.MobilityObservationFinding;
import com.example.movementstrategyassessment.models.MovementObservationFinding;
import com.example.movementstrategyassessment.models.StaticObservationFinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ResultListItemFragment extends Fragment {
    public static final String RESULT_ITEM = "RESULT_ITEM";
    private String itemText = "";
    private FragmentResultListItemBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentResultListItemBinding.inflate(inflater, container, false);
        Bundle args = getArguments();
        if (args != null) {
            this.itemText = args.getString(RESULT_ITEM);
        }
        return binding.getRoot(); //inflater.inflate(R.layout.fragment_observation_choice, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView resultItem = view.findViewById(R.id.result_item);
        resultItem.setText(this.itemText);
    }

   /* public boolean getIsSwitchChecked() {
        return this.isSwitchChecked;
    }

    public void setLabel(String newLabel) {
        this.label = newLabel;
    }

    public String getLabel() {
        return this.label;
    }

    public StaticObservationFinding getStaticFinding() {
        return staticFinding;
    }

    public MovementObservationFinding getMovementFinding() {
        return movementFinding;
    }

    public MobilityObservationFinding getMobFinding() {
        return mobFinding;
    }*/
}