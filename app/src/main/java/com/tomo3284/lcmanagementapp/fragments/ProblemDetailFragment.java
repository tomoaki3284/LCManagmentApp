package com.tomo3284.lcmanagementapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.view.arcseekbar.ArcSeekBar;
import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.Models.User;
import com.tomo3284.lcmanagementapp.Models.UserViewModel;
import com.tomo3284.lcmanagementapp.R;

public class ProblemDetailFragment extends Fragment {

    // constants
    public static final String TAG = ProblemDetailFragment.class.getSimpleName();

    // view related
    private MainActivity mParentActivity;
    private View mView;
    private TextView mProblemTitleTV;
    private TextView mDifficultyTV;
    private ArcSeekBar mEstimatedTimeBar;
    private ArcSeekBar mElapsedTimeBar;
    private EditText mNoteET;

    // variables
    private Problem mProblem;
    private User mUser;
    private UserViewModel mUserVM;

    public void setProblem(Problem problem) {
        mProblem = problem;
    }

    public void setParentActivity(MainActivity activity) {
        mParentActivity = activity;
    }

    public ProblemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_problem_detail, container, false);

        setupVM();
        setupButton();
        setupView();
        inflateView();

        return mView;
    }

    private void setupVM() {
        assert mParentActivity != null;
        mUserVM = new ViewModelProvider(mParentActivity).get(UserViewModel.class);
        mUserVM.getUser().observe(mParentActivity, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUser = user;
            }
        });
        mUser = mUserVM.getUser().getValue();
    }

    private void inflateView() {
        assert mProblem != null;
        mProblemTitleTV.setText("\"" + mProblem.getTitle() + "\"");
        mDifficultyTV.setText(mProblem.getDifficulty());
        if(mProblem.getDifficulty().equals("Easy")){
            mDifficultyTV.setTextColor(ContextCompat.getColor(getContext(), R.color.easyColor));
        } else if (mProblem.getDifficulty().equals("Medium")) {
            mDifficultyTV.setTextColor(ContextCompat.getColor(getContext(), R.color.mediumColor));
        } else {
            mDifficultyTV.setTextColor(ContextCompat.getColor(getContext(), R.color.hardColor));
        }
        mEstimatedTimeBar.showAnimation(mProblem.getEstimateTimeMin(), 1000);
        mEstimatedTimeBar.setLabelText(mProblem.getEstimateTimeMin() + "\nmin");
        // this checks out of bound
        int elapsedTimeMin = mProblem.getElapsedTimeMin();
        if(elapsedTimeMin > 60){
            elapsedTimeMin = 60;
        }
        mElapsedTimeBar.showAnimation(elapsedTimeMin, 1000);
        mElapsedTimeBar.setLabelText(mProblem.getElapsedTimeMin() + "\nmin");
        mNoteET.setText(mProblem.getNote());

        mEstimatedTimeBar.setEnabledDrag(false);
        mElapsedTimeBar.setEnabledDrag(false);
    }

    private void setupView() {
        mProblemTitleTV = mView.findViewById(R.id.problemTitle);
        mDifficultyTV = mView.findViewById(R.id.problemDifficulty);
        mEstimatedTimeBar = mView.findViewById(R.id.arcSeekBar);
        mElapsedTimeBar = mView.findViewById(R.id.arcSeekBar2);
        mNoteET = mView.findViewById(R.id.problemNote);
    }

    private void setupButton() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                assert mParentActivity != null;
                mParentActivity.popFragmentFromBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        ImageView backButton = mView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert mParentActivity != null;
                mParentActivity.popFragmentFromBackStack();
            }
        });

        ImageView trashButton = mView.findViewById(R.id.trashButton);
        trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/10/21 : generate dialog to confirm the deletion of the problem
                // make dialog that can remove classes
                new AlertDialog.Builder(getContext())
                        .setTitle("Remove Entry")
                        .setMessage("Are you sure to remove this problem from your history?")
                        .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mUser.getProblemList().getProblems().remove(mProblem);
                                mUserVM.setUser(mUser);
                                mParentActivity.popFragmentFromBackStack();
                            }
                        })
                        .show();
            }
        });
    }
}