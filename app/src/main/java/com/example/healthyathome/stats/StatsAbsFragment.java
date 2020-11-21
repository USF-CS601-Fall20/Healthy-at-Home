package com.example.healthyathome.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.healthyathome.HomeFragment;
import com.example.healthyathome.R;
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

/** StatsAbsFragment class representing the abs stats page. */
public class StatsAbsFragment extends Fragment{

    private TextView planksCount;
    private TextView crunchesCount;
    private TextView reverseCrunchesCount;
    private TextView russianTwistsCount;

    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    /**
     * Overrides the onCreateView method to display layout of the abs stats page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_stats_abs, container, false);

        planksCount = view.findViewById(R.id.plankNumText);
        crunchesCount = view.findViewById(R.id.crunchNumText);
        reverseCrunchesCount = view.findViewById(R.id.revCrunchNumText);
        russianTwistsCount = view.findViewById(R.id.russianTwistNumText);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Populate abs data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("abs");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            /**
             * Overrides the onEvent method to gather and display abs data from database.
             * @param value FirebaseFireStoneException
             * @param error DocumentSnapshot
             */
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                planksCount.setText(value.getString("plank"));
                crunchesCount.setText(value.getString("crunches"));
                reverseCrunchesCount.setText(value.getString("reverseCrunches"));
                russianTwistsCount.setText(value.getString("russianTwist"));
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
