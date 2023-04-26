package com.example.movementstrategyassessment.ui.assessment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movementstrategyassessment.R;
import com.example.movementstrategyassessment.databinding.FragmentObservationChoiceBinding;
import com.example.movementstrategyassessment.models.MobilityObservationFinding;
import com.example.movementstrategyassessment.models.MovementObservationFinding;
import com.example.movementstrategyassessment.models.StaticObservationFinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ObservationChoiceFragment extends Fragment {
    public static final String OBS_LABEL = "OBS_LABEL";
    public static final String OBS_STATIC = "OBS_STATIC";
    public static final String OBS_MVMT = "OBS_MVMT";
    public static final String OBS_MOB = "OBS_MOB";
    public static final String OBS_CHECKED = "OBS_CHECKED";
    private StaticObservationFinding staticFinding;
    private MovementObservationFinding movementFinding;
    private MobilityObservationFinding mobFinding;
    private String label;
    private boolean isSwitchChecked = false;
    private FragmentObservationChoiceBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentObservationChoiceBinding.inflate(inflater, container, false);
        Bundle args = getArguments();
        if (args != null) {
            this.label = args.getString(OBS_LABEL);
            this.isSwitchChecked = args.getBoolean(OBS_CHECKED);
            this.staticFinding = (StaticObservationFinding) args.getSerializable(OBS_STATIC);
            this.movementFinding = (MovementObservationFinding) args.getSerializable(OBS_MVMT);
            this.mobFinding = (MobilityObservationFinding) args.getSerializable(OBS_MOB);
        }
        return binding.getRoot();// inflater.inflate(R.layout.fragment_observation_choice, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView obsLabel = view.findViewById(R.id.observation_label);
        obsLabel.setText(this.label);
        SwitchMaterial obsSwitch = view.findViewById(R.id.observation_switch);
        obsSwitch.setActivated(this.isSwitchChecked);
    }

    public boolean getIsSwitchChecked() {
        return this.isSwitchChecked;
    }

    public void setLabel(String newLabel) {
        this.label = newLabel;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean getSwitchState() {
        boolean ret = false;
        View tempView = getView();
        if(tempView != null)
        {
            ret = ((SwitchMaterial)tempView.findViewById(R.id.observation_switch)).isChecked();
        }
        return ret;
    }

    public StaticObservationFinding getStaticFinding() {
        return staticFinding;
    }

    public MovementObservationFinding getMovementFinding() {
        return movementFinding;
    }

    public MobilityObservationFinding getMobFinding() {
        return mobFinding;
    }
}