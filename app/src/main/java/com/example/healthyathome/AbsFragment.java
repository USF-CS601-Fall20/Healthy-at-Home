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
