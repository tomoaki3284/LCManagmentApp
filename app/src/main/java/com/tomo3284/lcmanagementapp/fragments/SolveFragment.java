package com.tomo3284.lcmanagementapp.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.king.view.arcseekbar.ArcSeekBar;
import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.R;

public class SolveFragment extends Fragment {

    // constants
    public static final String TAG = SolveFragment.class.getSimpleName();
    private static final String EASY = "EASY";
    private static final String MEDIUM = "MEDIUM";
    private static final String HARD = "HARD";

    // view related
    private MainActivity mParentActivity;
    private View mView;
    private EditText mProblemTitleET;
    private EditText mProblemNumberET;

    // buttons
    private Button mEasyButton;
    private Button mMediumButton;
    private Button mHardButton;

    // normal variables
    private String mDifficulty = EASY;
    private int mEstimateTimeMin = 30;

    public SolveFragment() {
        // Required empty public constructor
    }

    public void setParentActivity(MainActivity activity) {
        mParentActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_solve, container, false);

        setupButtons();
        setupViews();

        return mView;
    }

    private void setupViews() {
        final ArcSeekBar arcSeekBar = mView.findViewById(R.id.arcSeekBar);
        arcSeekBar.setOnChangeListener(new ArcSeekBar.OnChangeListener() {
            @Override
            public void onStartTrackingTouch(boolean isCanDrag) {

            }

            @Override
            public void onProgressChanged(float progress, float max, boolean fromUser) {
                mEstimateTimeMin = (int) progress;
                String minInStr = mEstimateTimeMin + "\nmin";
                arcSeekBar.setLabelText(minInStr);
            }

            @Override
            public void onStopTrackingTouch(boolean isCanDrag) {

            }

            @Override
            public void onSingleTapUp() {

            }
        });

        mProblemTitleET = mView.findViewById(R.id.problemTitle);
        mProblemNumberET = mView.findViewById(R.id.problemNumber);
    }

    private void setupButtons() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // first fragment of bottom navigation, so it shouldn't be pop
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        Button startButton = mView.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String problemTitle = mProblemTitleET.getText().toString();
                if (mProblemNumberET.getText().toString().length() == 0) {
                    mProblemNumberET.setError("problem# cannot be empty");
                    return;
                }
                int problemNumber = Integer.parseInt(mProblemNumberET.getText().toString());

                if (!validInputs(problemTitle, problemNumber)) return;
                Problem problem = new Problem(mDifficulty, problemTitle, problemNumber);

                SolvingFragment solvingFragment = new SolvingFragment();
                solvingFragment.setParentActivity(mParentActivity);
                solvingFragment.setProblem(problem);
                mParentActivity.pushFragment(solvingFragment, SolvingFragment.TAG);
            }
        });

        mEasyButton = mView.findViewById(R.id.easyButton);
        mMediumButton = mView.findViewById(R.id.mediumButton);
        mHardButton = mView.findViewById(R.id.hardButton);

        mEasyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDifficultyTo(EASY);
            }
        });

        mMediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDifficultyTo(MEDIUM);
            }
        });

        mHardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDifficultyTo(HARD);
            }
        });

        // select easy as default difficulty
        setDifficultyTo(EASY);
    }

    private boolean validInputs(String title, int problemNumber) {
        if (title == null || title.length() == 0 || title.length() >= 50) {
            mProblemTitleET.setError("please enter valid title");
            return false;
        }
        if (problemNumber <= 0 || problemNumber >= 10000) {
            mProblemNumberET.setError("please enter valid problem#");
            return false;
        }
        return true;
    }

    private void setDifficultyTo(String difficulty) {
        // deselect highlight, and highlight the newly selected difficulty button
        deselectAllDifficultyButton();
        Drawable drawable;
        switch (difficulty) {
            case EASY:
                mDifficulty = EASY;
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.left_round_solid);
                DrawableCompat.setTint(drawable,
                        ResourcesCompat.getColor(getContext().getResources(), R.color.leetcodeMainColor, null));
                mEasyButton.setBackground(drawable);
                mEasyButton.setTextColor(Color.WHITE);
                break;

            case MEDIUM:
                mDifficulty = MEDIUM;
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.no_round_solid);
                DrawableCompat.setTint(drawable,
                        ResourcesCompat.getColor(getContext().getResources(), R.color.leetcodeMainColor, null));
                mMediumButton.setBackground(drawable);
                mMediumButton.setTextColor(Color.WHITE);
                break;

            case HARD:
                mDifficulty = HARD;
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.right_round_solid);
                DrawableCompat.setTint(drawable,
                        ResourcesCompat.getColor(getContext().getResources(), R.color.leetcodeMainColor, null));
                mHardButton.setBackground(drawable);
                mHardButton.setTextColor(Color.WHITE);
                break;
        }
    }

    private void deselectAllDifficultyButton() {
        int leetcodeColor = ResourcesCompat.getColor(getContext().getResources(), R.color.leetcodeMainColor, null);

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_rectangle_left_round);
        mEasyButton.setBackground(drawable);
        mEasyButton.setTextColor(leetcodeColor);

        drawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_rectangle_no_round);
        mMediumButton.setBackground(drawable);
        mMediumButton.setTextColor(leetcodeColor);

        drawable = ContextCompat.getDrawable(getContext(), R.drawable.shape_rectangle_right_round);
        mHardButton.setBackground(drawable);
        mHardButton.setTextColor(leetcodeColor);
    }
}