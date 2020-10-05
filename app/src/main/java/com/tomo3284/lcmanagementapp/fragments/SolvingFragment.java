package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.R;

public class SolvingFragment extends Fragment {

    // constants
    public static final String TAG = SolvingFragment.class.getSimpleName();

    // view related
    private MainActivity mParentActivity;
    private View mView;

    public SolvingFragment() {
        // Required empty public constructor
    }

    public void setParentActivity(MainActivity activity) {
        mParentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_solving, container, false);

        setupButtons();

        return mView;
    }

    private void setupButtons() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                popFragmentFromBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void popFragmentFromBackStack() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentManager.popBackStackImmediate();// pop current top fragment

        // make the top fragment visible
        int top = fragmentManager.getBackStackEntryCount() - 1;
        if (top >= 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(top);
            Fragment currentFragment = fragmentManager.findFragmentByTag(backStackEntry.getName());
            currentFragment.getView().setVisibility(View.VISIBLE);
        }
    }
}