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

public class CaloriesFragment extends Fragment{

    private int calories;
    private Spinner calorieSpinner;
    private TextView calorieCount;
    private String calorieString;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_calories, container, false);

        calorieSpinner = view.findViewById(R.id.calories);
        calorieCount = view.findViewById(R.id.calorieCount);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        // Setup user's calorie count
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    calorieString = value.getString("calories");
                    calories = Integer.parseInt(calorieString);
                    calorieCount.setText(calorieString);
                }
            }
        });

        // Submit Calories Button
        Button submitCalories = (Button) view.findViewById(R.id.caloriesEntryButton);
        submitCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calorieSubmission = calorieSpinner.getSelectedItem().toString();
                calories += Integer.parseInt(calorieSubmission);
                calorieString = String.valueOf(calories);

                DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                documentReference.update("calories", calorieString);
                Toast.makeText(getActivity().getBaseContext(), "Calories Submitted!", Toast.LENGTH_SHORT).show();
            }
        });

        // Reset Calories Button
        Button resetCalories = (Button) view.findViewById(R.id.resetCalButton);
        resetCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                documentReference.update("calories", "0");
                Toast.makeText(getActivity().getBaseContext(), "Calories Reset!", Toast.LENGTH_SHORT).show();
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
