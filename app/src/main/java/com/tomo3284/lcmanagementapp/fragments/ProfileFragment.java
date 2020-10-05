package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.R;

public class ProfileFragment extends Fragment {

    // constants
    public static final String TAG = ProfileFragment.class.getSimpleName();

    // view related
    private MainActivity mParentActivity;
    private View mView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public void setParentActivity(MainActivity mParentActivity) {
        this.mParentActivity = mParentActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);

        setupButtons();

        return mView;
    }

    private void setupButtons() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // first fragment of bottom navigation, so it shouldn't be pop
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
}