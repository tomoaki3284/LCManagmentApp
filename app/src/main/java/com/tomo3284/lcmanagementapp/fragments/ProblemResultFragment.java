package com.tomo3284.lcmanagementapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.Models.User;
import com.tomo3284.lcmanagementapp.Models.UserViewModel;
import com.tomo3284.lcmanagementapp.R;

public class ProblemResultFragment extends Fragment {

    // constants
    public static final String TAG = ProblemResultFragment.class.getSimpleName();

    // view related
    private View mView;

    // variables
    private Problem mProblem;
    private MainActivity mParentActivity;
    private User mUser;
    private UserViewModel mUserVM;

    public ProblemResultFragment() {
        // Required empty public constructor
    }

    public void setParentActivity(MainActivity activity) {
        mParentActivity = activity;
    }

    public void setProblem(Problem problem) {
        mProblem = problem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_problem_result, container, false);

        setupVM();
        setupButton();
        inflateViews();

        return mView;
    }

    private void inflateViews() {
        assert mProblem != null;
        TextView problemNumberTV = mView.findViewById(R.id.problemNumber);
        TextView title = mView.findViewById(R.id.problemTitle);
        TextView difficulty = mView.findViewById(R.id.problemDifficulty);
        TextView estimatedTimeTV = mView.findViewById(R.id.estimatedTV);
        TextView elapsedTimeTV = mView.findViewById(R.id.elapsedTV);
        problemNumberTV.setText(mProblem.getProblemNumber()+"");
        title.setText(mProblem.getTitle());
        difficulty.setText(mProblem.getDifficulty());
        estimatedTimeTV.setText(mProblem.getEstimateTimeMin()+"");
        elapsedTimeTV.setText(mProblem.getElapsedTimeMin()+"");
    }

    private void setupVM() {
        mUserVM = new ViewModelProvider(mParentActivity).get(UserViewModel.class);
        mUserVM.getUser().observe(mParentActivity, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mUser = user;
            }
        });
        mUser = mUserVM.getUser().getValue();
    }

    private void setupButton() {
        Button okButton = mView.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView noteET = mView.findViewById(R.id.noteET);
                mProblem.setNote(noteET.getText().toString());
                mParentActivity.popFragmentFromBackStack();
            }
        });
    }
}