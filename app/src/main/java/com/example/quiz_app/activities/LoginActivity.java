package com.example.quiz_app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_app.MainActivity;
import com.example.quiz_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    public static FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        String email = ((TextView) findViewById(R.id.email_tw)).getText().toString();
        String password = ((TextView) findViewById(R.id.password_tw)).getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill in email!.",
                    Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please fill in password!.",
                    Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("LOGIN SYSTEM LOG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                setUserInSharedPreferences(user);

                                Intent goToMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                                goToMainActivity.putExtra("changeUserInfo", true);
                                startActivity(goToMainActivity);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("LOGIN SYSTEM LOG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Incorrect credentials.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

    }

    private void setUserInSharedPreferences(FirebaseUser user) {
        SharedPreferences userSharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.userSharedPreferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userSharedPreferences.edit();
        editor.putString("user_email", user.getEmail());

        String domain = user.getEmail().split("@")[1];
        if (domain.split("\\.")[0] == "stud") {
            editor.putString("user_type", "Student");
        } else {
            editor.putString("user_type", "Professor");
        }
        editor.apply();
    }

    public void signUp(View view) {
        Intent goToSignUpActivity = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(goToSignUpActivity);
    }
}
