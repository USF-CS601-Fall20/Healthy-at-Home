package com.example.healthyathome.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthyathome.Abs;
import com.example.healthyathome.Arms;
import com.example.healthyathome.Cardio;
import com.example.healthyathome.Chest;
import com.example.healthyathome.Legs;
import com.example.healthyathome.R;
import com.example.healthyathome.Shoulders;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel categoriesViewModel;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

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
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
//                fTransaction.replace(R.id.content_frame_categories, fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Arms
        Button buttonBiceps = (Button)view.findViewById(R.id.armsButton);
        buttonBiceps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Arms();
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

        // Legs
        Button buttonQuads = (Button)view.findViewById(R.id.legsButton);
        buttonQuads.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new Legs();
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