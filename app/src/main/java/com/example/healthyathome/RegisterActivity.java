package com.example.healthyathome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/** RegisterActivity class representing the create account page. */
public class RegisterActivity extends AppCompatActivity {

    private EditText mUserName, mEmail, mPassword, mPhone;
    private Button registerButton, alreadyRegButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;

    /**
     * Loads the registration activity for the user.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserName = findViewById(R.id.userCreateText);
        mEmail = findViewById(R.id.emailCreateText);
        mPassword = findViewById(R.id.passCreateText);
        mPhone = findViewById(R.id.phoneCreateText);
        registerButton = findViewById(R.id.registerButton);
        alreadyRegButton = findViewById(R.id.skipRegisterButton);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        // If user is already logged in
        if (firebaseAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button
        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the register button responsibilities of
             * gathering user information data and determining if it is appropriate.
             * @param view View
             */
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String userName = mUserName.getText().toString();
                String phoneNumber = mPhone.getText().toString();

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

                // Register user
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    /**
                     * Overrides the onComplete method which determines if a registration was
                     * successful and also stores the user's account information in the database.
                     * @param task Task<AuthResult>
                     */
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();

                            // Get user ID and store data
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("userName", userName);
                            userMap.put("email", email);
                            userMap.put("phone", phoneNumber);
                            userMap.put("calories", "0");

                            documentReference.set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                /**
                                 * Overrides the onSuccess method to print success message for
                                 * user registration.
                                 * @param aVoid Void
                                 */
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "success: user profile created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                /**
                                 * Overrides the onFailure method to print failure message for
                                 * user registration.
                                 * @param e Exception
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "failure: " + e.toString());
                                }
                            });

                            // Abs exercise database
                            documentReference = firebaseFirestore.collection("users")
                                    .document(userID).collection("workouts")
                                    .document("abs");
                            Map<String, Object> absMap = new HashMap<>();
                            absMap.put("total", "0");
                            absMap.put("crunches", "0");
                            absMap.put("plank", "0");
                            absMap.put("reverseCrunches", "0");
                            absMap.put("russianTwist", "0");
                            documentReference.set(absMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                /**
                                 * Overrides the onSuccess method to print success message for
                                 * storing of abs data.
                                 * @param aVoid Void
                                 */
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "success: abs data created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                /**
                                 * Overrides the onFailure method to print failure message for
                                 * storing of abs data.
                                 * @param e Exception
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "failure: " + e.toString());
                                }
                            });

                            // Arms exercise database
                            documentReference = firebaseFirestore.collection("users")
                                    .document(userID).collection("workouts")
                                    .document("arms");
                            Map<String, Object> armsMap = new HashMap<>();
                            armsMap.put("total", "0");
                            armsMap.put("bicepCurl", "0");
                            armsMap.put("wristCurl", "0");
                            armsMap.put("tricepsKickback", "0");
                            armsMap.put("lateralRaise", "0");
                            documentReference.set(armsMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                /**
                                 * Overrides the onSuccess method to print success message for
                                 * storing of arms data.
                                 * @param aVoid Void
                                 */
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "success: arms data created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                /**
                                 * Overrides the onFailure method to print failure message for
                                 * storing of arms data.
                                 * @param e Exception
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "failure: " + e.toString());
                                }
                            });

                            // Cardio exercise database
                            documentReference = firebaseFirestore.collection("users")
                                    .document(userID).collection("workouts")
                                    .document("cardio");
                            Map<String, Object> cardioMap = new HashMap<>();
                            cardioMap.put("total", "0");
                            cardioMap.put("walk", "0");
                            cardioMap.put("jog", "0");
                            cardioMap.put("run", "0");
                            cardioMap.put("sprint", "0");
                            documentReference.set(cardioMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                /**
                                 * Overrides the onSuccess method to print success message for
                                 * storing of cardio data.
                                 * @param aVoid Void
                                 */
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "success: cardio data created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                /**
                                 * Overrides the onFailure method to print failure message for
                                 * storing of cardio data.
                                 * @param e Exception
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "failure: " + e.toString());
                                }
                            });

                            // Chest exercise database
                            documentReference = firebaseFirestore.collection("users")
                                    .document(userID).collection("workouts")
                                    .document("chest");
                            Map<String, Object> chestMap = new HashMap<>();
                            chestMap.put("total", "0");
                            chestMap.put("bench", "0");
                            chestMap.put("bridge", "0");
                            chestMap.put("lateralClimber", "0");
                            chestMap.put("pushup", "0");
                            documentReference.set(chestMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                /**
                                 * Overrides the onSuccess method to print success message for
                                 * storing of chest data.
                                 * @param aVoid Void
                                 */
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "success: chest data created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                /**
                                 * Overrides the onFailure method to print failure message for
                                 * storing of chest data.
                                 * @param e Exception
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "failure: " + e.toString());
                                }
                            });

                            // Legs exercise database
                            documentReference = firebaseFirestore.collection("users")
                                    .document(userID).collection("workouts")
                                    .document("legs");
                            Map<String, Object> legsMap = new HashMap<>();
                            legsMap.put("total", "0");
                            legsMap.put("calfRaise", "0");
                            legsMap.put("gluteBridge", "0");
                            legsMap.put("lunges", "0");
                            legsMap.put("squats", "0");
                            documentReference.set(legsMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                /**
                                 * Overrides the onSuccess method to print success message for
                                 * storing of legs data.
                                 * @param aVoid Void
                                 */
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "success: legs data created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                /**
                                 * Overrides the onFailure method to print failure message for
                                 * storing of legs data.
                                 * @param e Exception
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "failure: " + e.toString());
                                }
                            });

                            // Shoulder exercise database
                            documentReference = firebaseFirestore.collection("users")
                                    .document(userID).collection("workouts")
                                    .document("shoulders");
                            Map<String, Object> shouldersMap = new HashMap<>();
                            shouldersMap.put("total", "0");
                            shouldersMap.put("deltoidRaise", "0");
                            shouldersMap.put("frontRaise", "0");
                            shouldersMap.put("shoulderPress", "0");
                            shouldersMap.put("shoulderShrug", "0");
                            shouldersMap.put("uprightRow", "0");
                            documentReference.set(shouldersMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                /**
                                 * Overrides the onSuccess method to print success message for
                                 * storing of shoulders data.
                                 * @param aVoid Void
                                 */
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("TAG", "success: shoulder data created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                /**
                                 * Overrides the onFailure method to print failure message for
                                 * storing of shoulders data.
                                 * @param e Exception
                                 */
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "failure: " + e.toString());
                                }
                            });

                            // Start main activity
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // Already Registered Button
        alreadyRegButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the already registered button responsibility
             * of navigating the user to the login page.
             * @param view View
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}