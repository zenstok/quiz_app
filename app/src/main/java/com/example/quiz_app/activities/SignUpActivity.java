package com.example.quiz_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.quiz_app.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUp(View view) {
    }

    public void login(View view) {
        Intent goToLoginActivity = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(goToLoginActivity);
    }

}
