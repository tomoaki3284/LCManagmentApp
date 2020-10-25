package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tomo3284.lcmanagementapp.Activity.IntroActivity;
import com.tomo3284.lcmanagementapp.R;

public class IntroPage4Fragment extends Fragment {

    public IntroPage4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_intro_page4, container, false);

        LinearLayout layout = viewGroup.findViewById(R.id.layout_start_MainActivity);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((IntroActivity) getActivity()).navigateToMainActivity();
            }
        });

        return viewGroup;
    }
}