package com.derekprovance.biometricsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private TextView loginLockedTV;
    private TextView attemptsLeftTV;
    private TextView numberOfRemainingLoginAttemptsTV;
    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupVariables();
    }

    public void authenticateLogin(View view) {
        //TODO - this is an extremely poor implementation of authentication. The goal will eventually be
        //to implement Spring Security on the API side with accounts. For now this project is meant to be
        //more of an educational introduction into Android app developement
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Hello admin!", Toast.LENGTH_SHORT).show();
            SharedPreferences credPrefs = getSharedPreferences(Constants.CRED_PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = credPrefs.edit();

            editor.putString(Constants.CRED_NAME, username.getText().toString());
            editor.putString(Constants.CRED_PASS, password.getText().toString());
            editor.putBoolean(Constants.AUTHENTICATED, true);
            editor.apply();

            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "Seems like you 're not admin!",
                    Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;
            attemptsLeftTV.setVisibility(View.VISIBLE);
            numberOfRemainingLoginAttemptsTV.setVisibility(View.VISIBLE);
            numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));

            if (numberOfRemainingLoginAttempts == 0) {
                login.setEnabled(false);
                loginLockedTV.setVisibility(View.VISIBLE);
                loginLockedTV.setBackgroundColor(Color.RED);
                loginLockedTV.setText("LOGIN LOCKED!!!");
            }
        }
    }

    private void setupVariables() {
        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);
        login = findViewById(R.id.loginBtn);
        loginLockedTV = findViewById(R.id.loginLockedTV);
        attemptsLeftTV = findViewById(R.id.attemptsLeftTV);
        numberOfRemainingLoginAttemptsTV = findViewById(R.id.numberOfRemainingLoginAttemptsTV);
//        numberOfRemainingLoginAttemptsTV.setText(Integer.toString(numberOfRemainingLoginAttempts));
    }
}
