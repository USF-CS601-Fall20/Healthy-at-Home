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

/** CaloriesFragment class representing the calorie tracker page. */
public class CaloriesFragment extends Fragment{

    private int calories;
    private Spinner calorieSpinner;
    private TextView calorieCount;
    private String calorieString;
    private String userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    /**
     * Overrides the onCreateView method to display layout of the calories tracking page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
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
            /**
             * Overrides the onEvent method to gather calories data from database
             * @param value FirebaseFireStoneException
             * @param error DocumentSnapshot
             */
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
            /**
             * Overrides the onClick method to handle the submit calories button responsibilities
             * which include obtaining data and updating the database accordingly.
             * @param view View
             */
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
            /**
             * Overrides the onClick method to handle the reset calories button responsibility
             * of resetting calories count in the database to 0.
             * @param view View
             */
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
