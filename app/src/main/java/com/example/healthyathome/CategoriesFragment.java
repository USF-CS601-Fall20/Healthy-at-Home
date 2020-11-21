package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthyathome.AbsFragment;
import com.example.healthyathome.ArmsFragment;
import com.example.healthyathome.CardioFragment;
import com.example.healthyathome.ChestFragment;
import com.example.healthyathome.LegsFragment;
import com.example.healthyathome.R;
import com.example.healthyathome.ShouldersFragment;
import com.example.healthyathome.HomeFragment;

/** CategoriesFragment class representing the categories page. */
public class CategoriesFragment extends Fragment {

    /**
     * Overrides the onCreateView method to display layout of the categories page.
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View of the page
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // inflate layout
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

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

        // Abs
        Button buttonAbs = (Button)view.findViewById(R.id.absButton);
        buttonAbs.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the abs button responsibility of navigating
             * the user to the abs exercise page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new AbsFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Arms
        Button buttonBiceps = (Button)view.findViewById(R.id.armsButton);
        buttonBiceps.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the arms button responsibility of navigating
             * the user to the arms exercise page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new ArmsFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Cardio
        Button buttonCardio = (Button)view.findViewById(R.id.cardioButton);
        buttonCardio.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the cardio button responsibility of navigating
             * the user to the cardio exercise page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new CardioFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Chest
        Button buttonChest = (Button)view.findViewById(R.id.chestButton);
        buttonChest.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the chest button responsibility of navigating
             * the user to the chest exercise page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChestFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Legs
        Button buttonQuads = (Button)view.findViewById(R.id.legsButton);
        buttonQuads.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the legs button responsibility of navigating
             * the user to the legs exercise page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new LegsFragment();
                FragmentManager fManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fTransaction = fManager.beginTransaction();
                fTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragment);
                fTransaction.addToBackStack(null);
                fTransaction.commit();
            }
        });

        // Shoulders
        Button buttonShoulders = (Button)view.findViewById(R.id.shoulderButton);
        buttonShoulders.setOnClickListener(new View.OnClickListener(){
            /**
             * Overrides the onClick method to handle the shoulders button responsibility of navigating
             * the user to the shoulders exercise page.
             * @param v View
             */
            @Override
            public void onClick(View v) {
                Fragment fragment = new ShouldersFragment();
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