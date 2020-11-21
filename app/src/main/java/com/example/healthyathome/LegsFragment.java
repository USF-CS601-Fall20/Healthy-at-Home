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

/** LegsFragment class representing the legs exercise page. */
public class LegsFragment extends Fragment {

    private int calfRaiseCount;
    private int gluteBridgeCount;
    private int lungesCount;
    private int squatsCount;
    private int totalCount;

    private String calfRaiseCountString;
    private String gluteBridgeCountString;
    private String lungesCountString;
    private String squatsCountString;
    private String totalCountString;

    private Spinner legsSpinner;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    /**
     * Overrides the onCreateView method to display layout of the legs exercise page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_legs, container, false);

        legsSpinner = view.findViewById(R.id.legExerciseSpinner);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Setup user's legs data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("legs");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            /**
             * Overrides the onEvent method to gather legs data from database
             * @param value FirebaseFireStoneException
             * @param error DocumentSnapshot
             */
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    calfRaiseCountString = value.getString("calfRaise");
                    calfRaiseCount = Integer.parseInt(calfRaiseCountString);

                    gluteBridgeCountString = value.getString("gluteBridge");
                    gluteBridgeCount = Integer.parseInt(gluteBridgeCountString);

                    lungesCountString = value.getString("lunges");
                    lungesCount = Integer.parseInt(lungesCountString);

                    squatsCountString = value.getString("squats");
                    squatsCount = Integer.parseInt(squatsCountString);

                    totalCountString = value.getString("total");
                    totalCount = Integer.parseInt(totalCountString);
                }
            }
        });

        // Submit Legs Button
        Button submitLegs = (Button) view.findViewById(R.id.legsEntryButton);
        submitLegs.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the submit entry button responsibilities
             * which include obtaining data and updating the database accordingly.
             * @param view View
             */
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("legs");

                String selectedExercise = legsSpinner.getSelectedItem().toString();

                if (selectedExercise.equals("Calf Raise")){
                    calfRaiseCount++;
                    calfRaiseCountString = String.valueOf(calfRaiseCount);
                    documentReference.update("calfRaise", calfRaiseCountString);
                }else if (selectedExercise.equals("Glute Bridge")){
                    gluteBridgeCount++;
                    gluteBridgeCountString = String.valueOf(gluteBridgeCount);
                    documentReference.update("gluteBridge", gluteBridgeCountString);
                }else if (selectedExercise.equals("Lunges")){
                    lungesCount++;
                    lungesCountString = String.valueOf(lungesCount);
                    documentReference.update("lunges", lungesCountString);
                }
                else if (selectedExercise.equals("Squats")){
                    squatsCount++;
                    squatsCountString = String.valueOf(squatsCount);
                    documentReference.update("squats", squatsCountString);
                }

                totalCount++;
                totalCountString = String.valueOf(totalCount);
                documentReference.update("total", totalCountString);

                Toast.makeText(getActivity().getBaseContext(), "Legs Exercise Submitted!", Toast.LENGTH_SHORT).show();
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
