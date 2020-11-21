package com.example.healthyathome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/** LoginActivity class representing the login page. */
public class LoginActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button loginButton;
    private FirebaseAuth firebaseAuth;

    /**
     * Loads the login activity for the user.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.emailText);
        mPassword = findViewById(R.id.passText);
        loginButton = findViewById(R.id.loginButton);
        firebaseAuth = FirebaseAuth.getInstance();

        // Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the login button responsibilities of
             * navigating the user to the login page and checking if field are filled in correctly.
             * @param view View
             */
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("No email provided");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    mPassword.setError("No password provided");
                    return;
                }

                if (password.length() < 6){
                    mPassword.setError("Password must be at least 6 characters long");
                    return;
                }

                // Authenticate User
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    /**
                     * Overrides the onComplete method which determines if a login was successful.
                     * @param task Task<AuthResult>
                     */
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Create Account Button
        Button createAccButton = (Button) findViewById(R.id.createAccButton);
        createAccButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the create account button responsibility of
             * navigating the user to the create account page.
             * @param view View
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
