package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.Models.User;
import com.tomo3284.lcmanagementapp.Models.UserViewModel;
import com.tomo3284.lcmanagementapp.ProblemRecyclerViewAdapter;
import com.tomo3284.lcmanagementapp.R;

public class SolvedListFragment extends Fragment {

    // constants
    public static final String TAG = SolvedListFragment.class.getSimpleName();

    // view related
    private MainActivity mParentActivity;
    private View mView;
    private RecyclerView mRecyclerView;
    private ProblemRecyclerViewAdapter mProblemRecyclerViewAdapter;

    // variables
    private User mUser;
    private UserViewModel mUserVM;

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

        setupButtons();
        setupViewModels();
        setupRecyclerView();

        return mView;
    }

    private void setupRecyclerView() {
        mProblemRecyclerViewAdapter = new ProblemRecyclerViewAdapter(getContext(), mUser.getProblemList());
        mRecyclerView = mView.findViewById(R.id.recyclerviewSolvedList);
        mRecyclerView.setAdapter(mProblemRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupViewModels() {
        mUserVM = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        mUserVM.getUser().observe(mParentActivity, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUser = user;
            }
        });
        mUser = mUserVM.getUser().getValue();
        System.out.println(mUser);
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