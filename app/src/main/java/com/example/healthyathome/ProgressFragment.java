package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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