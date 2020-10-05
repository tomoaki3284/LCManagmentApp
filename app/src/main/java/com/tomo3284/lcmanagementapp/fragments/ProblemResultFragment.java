package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.R;

public class ProblemResultFragment extends Fragment {

    // view related
    private View mView;

    private Problem mProblem;

    public ProblemResultFragment() {
        // Required empty public constructor
    }

    public void setProblem(Problem problem) {
        mProblem = problem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_problem_result, container, false);



        return mView;
    }


}