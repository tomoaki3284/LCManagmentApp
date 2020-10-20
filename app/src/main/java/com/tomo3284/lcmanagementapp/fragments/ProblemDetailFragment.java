package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.king.view.arcseekbar.ArcSeekBar;
import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.Models.Problem;
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

        setupButton();
        setupView();
        inflateView();

        return mView;
    }

    private void inflateView() {
        assert mProblem != null;
        mProblemTitleTV.setText("\"" + mProblem.getTitle() + "\"");
        mDifficultyTV.setText(mProblem.getDifficulty());
        mEstimatedTimeBar.showAnimation(mProblem.getEstimateTimeMin(), 1000);
        mEstimatedTimeBar.setLabelText(mProblem.getEstimateTimeMin() + "\nmin");
        // TODO: might need to check out of bound for time minutes exceed 60 min
        mElapsedTimeBar.showAnimation(mProblem.getElapsedTimeMin(), 1000);
        mElapsedTimeBar.setLabelText(mProblem.getElapsedTimeMin() + "\nmin");
        mNoteET.setText(mProblem.getNote());
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
    }
}