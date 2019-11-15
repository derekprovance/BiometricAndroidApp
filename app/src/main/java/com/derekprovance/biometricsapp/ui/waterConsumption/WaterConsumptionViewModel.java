package com.derekprovance.biometricsapp.ui.waterConsumption;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WaterConsumptionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WaterConsumptionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is water consumption fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}