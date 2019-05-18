package com.example.quiz_app.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void createAccount(View view) {
        String email = ((TextView) findViewById(R.id.email_tw)).getText().toString();
        String password = ((TextView) findViewById(R.id.password_tw)).getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please fill in email!.",
                    Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Please fill in password!.",
                    Toast.LENGTH_SHORT).show();
        } else {
            final FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("LOGIN SYSTEM LOG", "createUserWithEmail:success");
                                Toast.makeText(SignUpActivity.this, "Your account has been created. You can log in now.",
                                        Toast.LENGTH_LONG).show();
                                goToLogin();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("LOGIN SYSTEM LOG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Password must be at least 6 character long!",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    public void login(View view) {
        goToLogin();
    }

    public void goToLogin() {
        Intent goToLoginActivity = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(goToLoginActivity);
    }
}
