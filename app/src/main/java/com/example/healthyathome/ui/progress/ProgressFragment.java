package com.example.healthyathome.ui.progress;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthyathome.R;
import com.example.healthyathome.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProgressFragment extends Fragment {

    private TextView userName, email, phoneNumber, progUserID;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        container.removeAllViews();
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
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