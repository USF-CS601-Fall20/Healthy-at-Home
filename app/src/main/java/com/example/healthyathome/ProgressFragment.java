package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthyathome.AbsFragment;
import com.example.healthyathome.R;
import com.example.healthyathome.HomeFragment;
import com.example.healthyathome.stats.StatsAbsFragment;
import com.example.healthyathome.stats.StatsArmsFragment;
import com.example.healthyathome.stats.StatsCardioFragment;
import com.example.healthyathome.stats.StatsChestFragment;
import com.example.healthyathome.stats.StatsLegsFragment;
import com.example.healthyathome.stats.StatsShouldersFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProgressFragment extends Fragment {

    private TextView absTotalCount;
    private TextView armsTotalCount;
    private TextView cardioTotalCount;
    private TextView chestTotalCount;
    private TextView legsTotalCount;
    private TextView shouldersTotalCount;
    private TextView userName, email, phoneNumber, progUserID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        absTotalCount = view.findViewById(R.id.absTotalText);
        armsTotalCount = view.findViewById(R.id.armsTotalText);
        cardioTotalCount = view.findViewById(R.id.cardioTotalText);
        chestTotalCount = view.findViewById(R.id.chestTotalText);
        legsTotalCount = view.findViewById(R.id.legsTotalText);
        shouldersTotalCount = view.findViewById(R.id.shouldersTotalText);

        userName = view.findViewById(R.id.usernameProgText);
        email = view.findViewById(R.id.emailProgText);
        phoneNumber = view.findViewById(R.id.phoneProgText);
        progUserID = view.findViewById(R.id.idProgText);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userID = firebaseAuth.getCurrentUser().getUid();

        // Populate user data
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userName.setText(value.getString("userName"));
                email.setText(value.getString("email"));
                phoneNumber.setText(value.getString("phone"));
                progUserID.setText(userID);
            }
        });

        // Populate abs data
        documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("abs");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                absTotalCount.setText(value.getString("total"));
            }
        });

        // Populate arms data
        documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("arms");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                armsTotalCount.setText(value.getString("total"));
            }
        });

        // Populate cardio data
        documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("cardio");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                cardioTotalCount.setText(value.getString("total"));
            }
        });

        // Populate chest data
        documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("chest");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                chestTotalCount.setText(value.getString("total"));
            }
        });

        // Populate legs data
        documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("legs");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                legsTotalCount.setText(value.getString("total"));
            }
        });

        // Populate shoulders data
        documentReference = firebaseFirestore.collection("users")
                .document(userID).collection("workouts").document("shoulders");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                shouldersTotalCount.setText(value.getString("total"));
            }
        });



        // Abs Stats Button
        ImageButton absProgButton = (ImageButton) view.findViewById(R.id.absProgButton);
        absProgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StatsAbsFragment fragment = new StatsAbsFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Arms Stats Button
        ImageButton armsProgButton = (ImageButton) view.findViewById(R.id.armsProgButton);
        armsProgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StatsArmsFragment fragment = new StatsArmsFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Cardio Stats Button
        ImageButton cardioProgButton = (ImageButton) view.findViewById(R.id.cardioProgButton);
        cardioProgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StatsCardioFragment fragment = new StatsCardioFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Chest Stats Button
        ImageButton chestProgButton = (ImageButton) view.findViewById(R.id.chestProgButton);
        chestProgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StatsChestFragment fragment = new StatsChestFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Legs Stats Button
        ImageButton legsProgButton = (ImageButton) view.findViewById(R.id.legsProgButton);
        legsProgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StatsLegsFragment fragment = new StatsLegsFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Shoulders Stats Button
        ImageButton shouldersProgButton = (ImageButton) view.findViewById(R.id.shouldersProgButton);
        shouldersProgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                StatsShouldersFragment fragment = new StatsShouldersFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Reset Progress Button
        Button resetProgressButton = (Button) view.findViewById(R.id.resetProgButton);
        resetProgressButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Reset abs progress
                DocumentReference documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("abs");
                documentReference.update("crunches", "0");
                documentReference.update("plank", "0");
                documentReference.update("reverseCrunches", "0");
                documentReference.update("russianTwist", "0");
                documentReference.update("total", "0");

                // Reset arms progress
                documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("arms");
                documentReference.update("bicepCurl", "0");
                documentReference.update("wristCurl", "0");
                documentReference.update("tricepsKickback", "0");
                documentReference.update("lateralRaise", "0");
                documentReference.update("total", "0");

                // Reset cardio progress
                documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("cardio");
                documentReference.update("walk", "0");
                documentReference.update("jog", "0");
                documentReference.update("run", "0");
                documentReference.update("sprint", "0");
                documentReference.update("total", "0");

                // Reset chest progress
                documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("chest");
                documentReference.update("bench", "0");
                documentReference.update("bridge", "0");
                documentReference.update("lateralClimber", "0");
                documentReference.update("pushup", "0");
                documentReference.update("total", "0");

                // Reset legs progress
                documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("legs");
                documentReference.update("calfRaise", "0");
                documentReference.update("gluteBridge", "0");
                documentReference.update("lunges", "0");
                documentReference.update("squats", "0");
                documentReference.update("total", "0");

                // Reset shoulders progress
                documentReference = firebaseFirestore.collection("users")
                        .document(userID).collection("workouts").document("shoulders");
                documentReference.update("deltoidRaise", "0");
                documentReference.update("frontRaise", "0");
                documentReference.update("shoulderPress", "0");
                documentReference.update("shoulderShrug", "0");
                documentReference.update("uprightRow", "0");
                documentReference.update("total", "0");
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