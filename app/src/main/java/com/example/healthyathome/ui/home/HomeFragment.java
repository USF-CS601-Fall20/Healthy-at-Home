package com.example.healthyathome.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthyathome.R;

import java.util.Random;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private String[] healthTips = new String[]{
            "Stay healthy at home!",
            "Drink water, stay hydrated.",
            "Eat plenty of fruits and vegetables.",
            "Make time for a walk/run every day.",
            "Wash your hands at least 5 times a day",
            "Get plenty of sleep."
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tipText = (TextView) view.findViewById(R.id.tipText);
        Button tipButton = (Button) view.findViewById(R.id.tipButton);


        tipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Random random = new Random();
                int healthTipsIndex = random.nextInt(healthTips.length);
                tipText.setText(healthTips[healthTipsIndex]);
            }
        });

        return view;
    }
}