package com.tomo3284.lcmanagementapp.fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.king.view.arcseekbar.ArcSeekBar;
import com.tomo3284.lcmanagementapp.MainActivity;
import com.tomo3284.lcmanagementapp.R;

public class SolveFragment extends Fragment {

    // constants
    private static final String EASY = "EASY";
    private static final String MEDIUM = "MEDIUM";
    private static final String HARD = "HARD";

    // view related
    private MainActivity mParentActivity;
    private View mView;

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
    }

    private void setupButtons() {
        Button startButton = mView.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/10/05 - load next next fragment through parent activity
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