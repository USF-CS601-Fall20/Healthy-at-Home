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

/** ArmsFragment class representing the arms exercise page. */
public class ArmsFragment extends Fragment {

    private int bicepCurlCount;
    private int wristCurlCount;
    private int tricepKickCount;
    private int lateralRaiseCount;
    private int totalCount;

    private String bicepCurlCountString;
    private String wristCurlCountString;
    private String reverseCrunchesCountString;
    private String lateralRaiseCountString;
    private String totalCountString;

    private Spinner armsSpinner;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    /**
     * Overrides the onCreateView method to display layout of the arms exercise page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_arms, container, false);

        armsSpinner = view.findViewById(R.id.armsExerciseSpinner);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Setup user's arms data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("arms");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            /**
             * Overrides the onEvent method to gather arms data from database
             * @param value FirebaseFireStoneException
             * @param error DocumentSnapshot
             */
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    bicepCurlCountString = value.getString("bicepCurl");
                    bicepCurlCount = Integer.parseInt(bicepCurlCountString);

                    wristCurlCountString = value.getString("wristCurl");
                    wristCurlCount = Integer.parseInt(wristCurlCountString);

                    reverseCrunchesCountString = value.getString("tricepsKickback");
                    tricepKickCount = Integer.parseInt(reverseCrunchesCountString);

                    lateralRaiseCountString = value.getString("lateralRaise");
                    lateralRaiseCount = Integer.parseInt(lateralRaiseCountString);

                    totalCountString = value.getString("total");
                    totalCount = Integer.parseInt(totalCountString);
                }
            }
        });

        // Submit Arms Button
        Button submitArms = (Button) view.findViewById(R.id.armsEntryButton);
        submitArms.setOnClickListener(new View.OnClickListener() {
            /**
             * Overrides the onClick method to handle the submit entry button responsibilities
             * which include obtaining data and updating the database accordingly.
             * @param view View
             */
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("arms");

                String selectedExercise = armsSpinner.getSelectedItem().toString();

                if (selectedExercise.equals("Bicep Curl")){
                    bicepCurlCount++;
                    bicepCurlCountString = String.valueOf(bicepCurlCount);
                    documentReference.update("bicepCurl", bicepCurlCountString);
                }else if (selectedExercise.equals("Wrist Curl")){
                    wristCurlCount++;
                    wristCurlCountString = String.valueOf(wristCurlCount);
                    documentReference.update("wristCurl", wristCurlCountString);
                }else if (selectedExercise.equals("Triceps Kickback")){
                    tricepKickCount++;
                    reverseCrunchesCountString = String.valueOf(tricepKickCount);
                    documentReference.update("tricepsKickback", reverseCrunchesCountString);
                }
                else if (selectedExercise.equals("Lateral Raise")){
                    lateralRaiseCount++;
                    lateralRaiseCountString = String.valueOf(lateralRaiseCount);
                    documentReference.update("lateralRaise", lateralRaiseCountString);
                }

                totalCount++;
                totalCountString = String.valueOf(totalCount);
                documentReference.update("total", totalCountString);

                Toast.makeText(getActivity().getBaseContext(), "Arms Exercise Submitted!", Toast.LENGTH_SHORT).show();
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
