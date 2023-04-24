package com.example.movementstrategyassessment.ui.assessment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AssessmentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AssessmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Assessment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}