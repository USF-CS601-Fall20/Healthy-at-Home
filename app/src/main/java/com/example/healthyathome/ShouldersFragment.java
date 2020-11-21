package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/** ShouldersFragment class representing the shoulders exercise page. */
public class ShouldersFragment extends Fragment {

    private int deltoidRaiseCount;
    private int frontRaiseCount;
    private int shoulderPressCount;
    private int shoulderShrugCount;
    private int uprightRowCount;
    private int totalCount;

    private String deltoidRaiseCountString;
    private String frontRaiseCountString;
    private String shoulderPressCountString;
    private String shoulderShrugCountString;
    private String uprightRowCountString;
    private String totalCountString;

    private Spinner shouldersSpinner;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    /**
     * Overrides the onCreateView method to display layout of the shoulders exercise page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shoulders, container, false);

        shouldersSpinner = view.findViewById(R.id.shoulderExerciseSpinner);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Setup user's shoulders data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("shoulders");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            /**
             * Overrides the onEvent method to gather shoulders data from database
             * @param value FirebaseFireStoneException
             * @param error DocumentSnapshot
             */
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    deltoidRaiseCountString = value.getString("deltoidRaise");
                    deltoidRaiseCount = Integer.parseInt(deltoidRaiseCountString);

                    frontRaiseCountString = value.getString("frontRaise");
                    frontRaiseCount = Integer.parseInt(frontRaiseCountString);

                    shoulderPressCountString = value.getString("shoulderPress");
                    shoulderPressCount = Integer.parseInt(shoulderPressCountString);

                    shoulderShrugCountString = value.getString("shoulderShrug");
                    shoulderShrugCount = Integer.parseInt(shoulderShrugCountString);

                    uprightRowCountString = value.getString("uprightRow");
                    uprightRowCount = Integer.parseInt(uprightRowCountString);

                    totalCountString = value.getString("total");
                    totalCount = Integer.parseInt(totalCountString);
                }
            }
        });

        // Submit Shoulders Button
        Button submitShoulders = (Button) view.findViewById(R.id.shoulderEntryButton);
        submitShoulders.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the submit entry button responsibilities
             * which include obtaining data and updating the database accordingly.
             * @param view View
             */
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("shoulders");

                String selectedExercise = shouldersSpinner.getSelectedItem().toString();

                if (selectedExercise.equals("Deltoid Raise")){
                    deltoidRaiseCount++;
                    deltoidRaiseCountString = String.valueOf(deltoidRaiseCount);
                    documentReference.update("deltoidRaise", deltoidRaiseCountString);
                }else if (selectedExercise.equals("Front Raise")){
                    frontRaiseCount++;
                    frontRaiseCountString = String.valueOf(frontRaiseCount);
                    documentReference.update("frontRaise", frontRaiseCountString);
                }else if (selectedExercise.equals("Shoulder Press")){
                    shoulderPressCount++;
                    shoulderPressCountString = String.valueOf(shoulderPressCount);
                    documentReference.update("shoulderPress", shoulderPressCountString);
                }else if (selectedExercise.equals("Shoulder Shrug")){
                    shoulderShrugCount++;
                    shoulderShrugCountString = String.valueOf(shoulderShrugCount);
                    documentReference.update("shoulderShrug", shoulderShrugCountString);
                }else if (selectedExercise.equals("Upright Row")){
                    uprightRowCount++;
                    uprightRowCountString = String.valueOf(uprightRowCount);
                    documentReference.update("uprightRow", uprightRowCountString);
                }

                totalCount++;
                totalCountString = String.valueOf(totalCount);
                documentReference.update("total", totalCountString);

                Toast.makeText(getActivity().getBaseContext(), "Shoulders Exercise Submitted!", Toast.LENGTH_SHORT).show();
            }
        });

        // Home button
        ImageButton homeButton = (ImageButton) view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the home button responsibility of navigating
             * the user back to the application's home page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        return view;
    }
}
