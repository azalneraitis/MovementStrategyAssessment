package com.example.movementstrategyassessment;

import android.os.Bundle;

import com.example.movementstrategyassessment.models.AssessmentResultService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.movementstrategyassessment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    AssessmentResultService assessmentResultsService = new AssessmentResultService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_assessment, R.id.navigation_results, R.id.navigation_research)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        this.createAssessmentResultService();
    }

    public AssessmentResultService getAssessmentResultsService() {
        if (this.assessmentResultsService == null) {
            return this.createAssessmentResultService();
        } else {
            return this.assessmentResultsService;
        }
    }

    private AssessmentResultService createAssessmentResultService() {
        return this.assessmentResultsService == null ? new AssessmentResultService() : this.assessmentResultsService;
    }
}