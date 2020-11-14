package com.example.healthyathome.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthyathome.Abs;
import com.example.healthyathome.Biceps;
import com.example.healthyathome.Cardio;
import com.example.healthyathome.Chest;
import com.example.healthyathome.Quads;
import com.example.healthyathome.R;
import com.example.healthyathome.Shoulders;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel =
//                new ViewModelProvider(this).get(NotificationsViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // inflate layout
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        // Abs
        Button buttonAbs = (Button)view.findViewById(R.id.absButton);
        buttonAbs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Abs();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(R.id.content_frame_categories, fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Biceps
        Button buttonBiceps = (Button)view.findViewById(R.id.bicepsButton);
        buttonBiceps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Biceps();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(R.id.content_frame_categories, fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Cardio
        Button buttonCardio = (Button)view.findViewById(R.id.cardioButton);
        buttonCardio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Cardio();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(R.id.content_frame_categories, fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Chest
        Button buttonChest = (Button)view.findViewById(R.id.chestButton);
        buttonChest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Chest();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(R.id.content_frame_categories, fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Quads
        Button buttonQuads = (Button)view.findViewById(R.id.quadsButton);
        buttonQuads.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Quads();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(R.id.content_frame_categories, fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Shoulders
        Button buttonShoulders = (Button)view.findViewById(R.id.shoulderButton);
        buttonShoulders.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Shoulders();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(R.id.content_frame_categories, fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });
        return view;
    }
}