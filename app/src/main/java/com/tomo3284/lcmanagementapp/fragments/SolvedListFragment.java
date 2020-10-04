package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.R;

public class SolvedListFragment extends Fragment {

    private MainActivity mParentActivity;
    private View mView;

    public SolvedListFragment() {
        // Required empty public constructor
    }

    public void setParentActivity(MainActivity activity) {
        mParentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_solved_list, container, false);


        return mView;
    }
}