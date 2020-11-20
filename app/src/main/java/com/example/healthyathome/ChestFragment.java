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

public class ChestFragment extends Fragment {

    private int benchCount;
    private int bridgeFlyCount;
    private int lateralClimbCount;
    private int pushupCount;
    private int totalCount;

    private String benchCountString;
    private String bridgeFlyCountString;
    private String lateralClimbCountString;
    private String pushupCountString;
    private String totalCountString;

    private Spinner chestSpinner;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_chest, container, false);

        chestSpinner = view.findViewById(R.id.chestExerciseSpinner);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Setup user's chest data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("chest");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                benchCountString = value.getString("bench");
                benchCount = Integer.parseInt(benchCountString);

                bridgeFlyCountString = value.getString("bridge");
                bridgeFlyCount = Integer.parseInt(bridgeFlyCountString);

                lateralClimbCountString = value.getString("lateralClimber");
                lateralClimbCount = Integer.parseInt(lateralClimbCountString);

                pushupCountString = value.getString("pushup");
                pushupCount = Integer.parseInt(pushupCountString);

                totalCountString = value.getString("total");
                totalCount = Integer.parseInt(totalCountString);
            }
        });

        // Submit Chest Button
        Button submitChest = (Button) view.findViewById(R.id.chestEntryButton);
        submitChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("chest");

                String selectedExercise = chestSpinner.getSelectedItem().toString();

                if (selectedExercise.equals("Bench")){
                    benchCount++;
                    benchCountString = String.valueOf(benchCount);
                    documentReference.update("bench", benchCountString);
                }else if (selectedExercise.equals("Bridge Fly")){
                    bridgeFlyCount++;
                    bridgeFlyCountString = String.valueOf(bridgeFlyCount);
                    documentReference.update("bridge", bridgeFlyCountString);
                }else if (selectedExercise.equals("Lateral Climber")){
                    lateralClimbCount++;
                    lateralClimbCountString = String.valueOf(lateralClimbCount);
                    documentReference.update("lateralClimber", lateralClimbCountString);
                }
                else if (selectedExercise.equals("Pushup")){
                    pushupCount++;
                    pushupCountString = String.valueOf(pushupCount);
                    documentReference.update("pushup", pushupCountString);
                }

                totalCount++;
                totalCountString = String.valueOf(totalCount);
                documentReference.update("total", totalCountString);

                Toast.makeText(getActivity().getBaseContext(), "Chest Exercise Submitted!", Toast.LENGTH_SHORT).show();
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
