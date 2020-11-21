package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthyathome.CaloriesFragment;
import com.example.healthyathome.R;
import com.example.healthyathome.CategoriesFragment;
import com.example.healthyathome.ProgressFragment;

import java.util.Random;

/** HomeFragment class representing the home page. */
public class HomeFragment extends Fragment {

    private String[] healthTips = new String[]{
            "Stay healthy at home!",
            "Drink water, stay hydrated.",
            "Eat plenty of fruits and vegetables.",
            "Make time for a walk/run every day.",
            "Wash your hands at least 5 times a day",
            "Get plenty of sleep."
    };

    /**
     * Overrides the onCreateView method to display layout of the home page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Randomize Health Tips
        TextView tipText = (TextView) view.findViewById(R.id.tipText);
        Button tipButton = (Button) view.findViewById(R.id.tipButton);
        tipButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the tip button responsibility of
             * displaying a random health tip to the user.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int healthTipsIndex = random.nextInt(healthTips.length);
                tipText.setText(healthTips[healthTipsIndex]);
            }
        });

        // Categories Button
        Button categoriesButton = (Button) view.findViewById(R.id.categoriesButton);
        categoriesButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the categories button responsibility of
             * navigating the user to the categories page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new CategoriesFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Progress Button
        Button progressButton = (Button) view.findViewById(R.id.progressButton);
        progressButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the progress button responsibility of
             * navigating the user to the progress page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProgressFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Calories Button
        Button caloriesButton = (Button) view.findViewById(R.id.caloriesButton);
        caloriesButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the calories button responsibility of
             * navigating the user to the calories tracker page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new CaloriesFragment();
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