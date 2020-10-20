package com.tomo3284.lcmanagementapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.Models.ProblemList;
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
    private ImageView mSortButton;

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
        mProblemRecyclerViewAdapter = new ProblemRecyclerViewAdapter(getContext(), mUser.getProblemList(), mParentActivity);
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
        assert mUser != null;
    }

    private void setupButtons() {
        mSortButton = mView.findViewById(R.id.sortButton);
        mSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/10/13 - generate dialog, let user pick which sort
                showSortDialog();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // first fragment of bottom navigation, so it shouldn't be pop
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void showSortDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mParentActivity);
        alertDialog.setTitle("Sorting Option");
        String[] items = {"Problem# ↑", "Problem# ↓",
                "Difficulty ↑", "Difficulty ↓",
                "Title ↑", "Title ↓",
                "Completeness"
        };
        int checkedItem = 1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        mUser.getProblemList().sortByProblemNumber(true);
                        break;
                    case 1:
                        mUser.getProblemList().sortByProblemNumber(false);
                        break;
                    case 2:
                        mUser.getProblemList().sortByDifficulty(true);
                        break;
                    case 4:
                        mUser.getProblemList().sortByDifficulty(false);
                        break;
                    case 5:
                        mUser.getProblemList().sortByTitle(true);
                        break;
                    case 6:
                        mUser.getProblemList().sortByTitle(false);
                        break;
                    case 7:
                        mUser.getProblemList().sortByCompleteness();
                        break;
                }
                updateRecycleView();
                dialog.dismiss();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void updateRecycleView() {
        mProblemRecyclerViewAdapter.notifyDataSetChanged();
    }
}