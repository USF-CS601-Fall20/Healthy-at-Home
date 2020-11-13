package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class CategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        // inflate layout
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Abs
        Button buttonAbs = (Button)view.findViewById(R.id.absButton);
        buttonAbs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Abs());
                fr.commit();
            }
        });

        // Biceps
        Button buttonBiceps = (Button)view.findViewById(R.id.bicepsButton);
        buttonBiceps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Biceps());
                fr.commit();
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
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Chest());
                fr.commit();
            }
        });

        // Quads
        Button buttonQuads = (Button)view.findViewById(R.id.quadsButton);
        buttonQuads.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Quads());
                fr.commit();
            }
        });

        // Shoulders
        Button buttonShoulders = (Button)view.findViewById(R.id.shoulderButton);
        buttonShoulders.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Shoulders());
                fr.commit();
            }
        });
        return view;
    }
}
