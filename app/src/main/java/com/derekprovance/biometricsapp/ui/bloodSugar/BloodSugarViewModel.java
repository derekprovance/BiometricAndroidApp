package com.derekprovance.biometricsapp.ui.bloodSugar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BloodSugarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BloodSugarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is blood sugar fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}