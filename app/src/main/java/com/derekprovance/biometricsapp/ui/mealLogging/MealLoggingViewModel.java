package com.derekprovance.biometricsapp.ui.mealLogging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MealLoggingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MealLoggingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is meal logging fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}