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

public class CardioFragment extends Fragment {

    private int walkCount;
    private int jogCount;
    private int runCount;
    private int sprintCount;
    private int totalCount;

    private String walkCountString;
    private String jogCountString;
    private String runCountString;
    private String sprintCountString;
    private String totalCountString;

    private Spinner cardioSpinner;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_cardio, container, false);

        cardioSpinner = view.findViewById(R.id.cardioExerciseSpinner);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Setup user's cardio data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("cardio");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                walkCountString = value.getString("walk");
                walkCount = Integer.parseInt(walkCountString);

                jogCountString = value.getString("jog");
                jogCount = Integer.parseInt(jogCountString);

                runCountString = value.getString("run");
                runCount = Integer.parseInt(runCountString);

                sprintCountString = value.getString("sprint");
                sprintCount = Integer.parseInt(sprintCountString);

                totalCountString = value.getString("total");
                totalCount = Integer.parseInt(totalCountString);
            }
        });

        // Submit Cardio Button
        Button submitCardio = (Button) view.findViewById(R.id.cardioEntryButton);
        submitCardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("cardio");

                String selectedExercise = cardioSpinner.getSelectedItem().toString();

                if (selectedExercise.equals("Walk")){
                    walkCount++;
                    walkCountString = String.valueOf(walkCount);
                    documentReference.update("walk", walkCountString);
                }else if (selectedExercise.equals("Jog")){
                    jogCount++;
                    jogCountString = String.valueOf(jogCount);
                    documentReference.update("jog", jogCountString);
                }else if (selectedExercise.equals("Run")){
                    runCount++;
                    runCountString = String.valueOf(runCount);
                    documentReference.update("run", runCountString);
                }
                else if (selectedExercise.equals("Sprint")){
                    sprintCount++;
                    sprintCountString = String.valueOf(sprintCount);
                    documentReference.update("sprint", sprintCountString);
                }

                totalCount++;
                totalCountString = String.valueOf(totalCount);
                documentReference.update("total", totalCountString);

                Toast.makeText(getActivity().getBaseContext(), "Cardio Exercise Submitted!", Toast.LENGTH_SHORT).show();
            }
        });

        // Home button
        ImageButton homeButton = (ImageButton) view.findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
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
