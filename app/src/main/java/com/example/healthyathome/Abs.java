package com.example.healthyathome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Abs extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

//        dashboardViewModel =
//                new ViewModelProvider(this).get(DashboardViewModel.class);




        View view = inflater.inflate(R.layout.fragment_abs, container, false);
        return view;
    }
}
