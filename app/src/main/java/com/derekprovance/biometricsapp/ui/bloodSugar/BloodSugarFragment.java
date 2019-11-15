package com.derekprovance.biometricsapp.ui.bloodSugar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.derekprovance.biometricsapp.R;

public class BloodSugarFragment extends Fragment {

    private BloodSugarViewModel bloodSugarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bloodSugarViewModel =
                ViewModelProviders.of(this).get(BloodSugarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_blood_sugar, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        bloodSugarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}