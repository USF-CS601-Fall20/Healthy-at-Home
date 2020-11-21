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

/** StatsArmsFragment class representing the arms stats page. */
public class StatsArmsFragment extends Fragment {

    private TextView bicepCurlCount;
    private TextView wristCurlCount;
    private TextView tricepKickCount;
    private TextView lateralRaiseCount;

    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    /**
     * Overrides the onCreateView method to display layout of the arms stats page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_stats_arms, container, false);

        bicepCurlCount = view.findViewById(R.id.bicepCurlNumText);
        wristCurlCount = view.findViewById(R.id.wristCurlNumText);
        tricepKickCount = view.findViewById(R.id.tricepKickNumText);
        lateralRaiseCount = view.findViewById(R.id.lateralRaiseNumText);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Populate arms data
        DocumentReference documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("arms");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            /**
             * Overrides the onEvent method to gather and display arms data from database.
             * @param value FirebaseFireStoneException
             * @param error DocumentSnapshot
             */
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                bicepCurlCount.setText(value.getString("bicepCurl"));
                wristCurlCount.setText(value.getString("wristCurl"));
                tricepKickCount.setText(value.getString("tricepsKickback"));
                lateralRaiseCount.setText(value.getString("lateralRaise"));
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
