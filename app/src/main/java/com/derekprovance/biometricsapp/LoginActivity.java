package com.derekprovance.biometricsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.derekprovance.biometricsapp.auth.AuthenticationTask;
import com.derekprovance.biometricsapp.auth.OnEventListener;
import com.derekprovance.biometricsapp.util.Constants;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupVariables();
    }

    public void authenticateLogin(View view) {
        //TODO - this is an extremely poor implementation of authentication. The goal will eventually be
        //to implement Spring Security on the API side with accounts. For now this project is meant to be
        //more of an educational introduction into Android app development

        final String usernameInput = username.getText().toString();
        final String passwordInput = password.getText().toString();
        final Intent mainActivity = new Intent(this, MainActivity.class);

        AuthenticationTask auth = new AuthenticationTask(new OnEventListener<Boolean>() {
            @Override
            public void onSuccess(Boolean result) {
                saveLoginCredentials(usernameInput, passwordInput);
                startActivity(mainActivity);
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getApplicationContext(), "Unable to log in", Toast.LENGTH_SHORT).show();
            }
        });

        auth.execute(usernameInput, passwordInput);
    }

    private void saveLoginCredentials(String username, String password) {
        SharedPreferences credPrefs = getSharedPreferences(Constants.CRED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = credPrefs.edit();

        editor.putString(Constants.CRED_NAME, username);
        editor.putString(Constants.CRED_PASS, password);
        editor.putBoolean(Constants.AUTHENTICATED, true);
        editor.apply();
    }

    private void setupVariables() {
        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);
    }
}
