package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthyathome.CategoriesFragment;
import com.example.healthyathome.HomeFragment;
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

/** AbsFragment class representing the abs exercise page. */
public class AbsFragment extends Fragment {

    private int crunchesCount;
    private int plankCount;
    private int reverseCrunchesCount;
    private int russianTwistCount;
    private int totalCount;

    private String crunchesCountString;
    private String plankCountString;
    private String reverseCrunchesCountString;
    private String russianTwistCountString;
    private String totalCountString;

    private Spinner absSpinner;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    /**
     * Overrides the onCreateView method to display layout of the abs exercise page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_abs, container, false);

        absSpinner = view.findViewById(R.id.absExerciseSpinner);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Setup user's abs data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("abs");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            /**
             * Overrides the onEvent method to gather abs data from database
             * @param value FirebaseFireStoneException
             * @param error DocumentSnapshot
             */
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    crunchesCountString = value.getString("crunches");
                    crunchesCount = Integer.parseInt(crunchesCountString);

                    plankCountString = value.getString("plank");
                    plankCount = Integer.parseInt(plankCountString);

                    reverseCrunchesCountString = value.getString("reverseCrunches");
                    reverseCrunchesCount = Integer.parseInt(reverseCrunchesCountString);

                    russianTwistCountString = value.getString("russianTwist");
                    russianTwistCount = Integer.parseInt(russianTwistCountString);

                    totalCountString = value.getString("total");
                    totalCount = Integer.parseInt(totalCountString);
                }
            }
        });

        // Submit Abs Button
        Button submitAbs = (Button) view.findViewById(R.id.absEntryButton);
        submitAbs.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the submit entry button responsibilities
             * which include obtaining data and updating the database accordingly.
             * @param view View
             */
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("abs");

                String selectedExercise = absSpinner.getSelectedItem().toString();

                if (selectedExercise.equals("Crunches")){
                    crunchesCount++;
                    crunchesCountString = String.valueOf(crunchesCount);
                    documentReference.update("crunches", crunchesCountString);
                }else if (selectedExercise.equals("Plank")){
                    plankCount++;
                    plankCountString = String.valueOf(plankCount);
                    documentReference.update("plank", plankCountString);
                }else if (selectedExercise.equals("Reverse Crunches")){
                    reverseCrunchesCount++;
                    reverseCrunchesCountString = String.valueOf(reverseCrunchesCount);
                    documentReference.update("reverseCrunches", reverseCrunchesCountString);
                }
                else if (selectedExercise.equals("Russian Twist")){
                    russianTwistCount++;
                    russianTwistCountString = String.valueOf(russianTwistCount);
                    documentReference.update("russianTwist", russianTwistCountString);
                }

                totalCount++;
                totalCountString = String.valueOf(totalCount);
                documentReference.update("total", totalCountString);

                Toast.makeText(getActivity().getBaseContext(), "Abs Exercise Submitted!", Toast.LENGTH_SHORT).show();
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
