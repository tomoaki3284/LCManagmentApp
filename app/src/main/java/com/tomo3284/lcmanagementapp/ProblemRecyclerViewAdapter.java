package com.tomo3284.lcmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.Models.ProblemList;
import com.tomo3284.lcmanagementapp.fragments.ProblemDetailFragment;

public class ProblemRecyclerViewAdapter extends RecyclerView.Adapter<ProblemRecyclerViewAdapter.ProblemViewHolder> {

    private Context mContext;
    private ProblemList mProblemList;
    private MainActivity mParentActivity;

    public ProblemRecyclerViewAdapter(Context context, ProblemList problemList, MainActivity activity) {
        mContext = context;
        mProblemList = problemList;
        mParentActivity = activity;
    }

    @NonNull
    @Override
    public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.problem_list_item_layout, parent, false);

        return new ProblemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemViewHolder holder, int position) {
        Problem problem = mProblemList.getProblems().get(position);
        holder.titleTV.setText(problem.getTitle());
        holder.difficultyTV.setText(problem.getDifficulty());
        int colorId = 0;
        if (problem.getDifficulty().equals("hard")) {
            colorId = R.color.hardColor;
        } else if (problem.getDifficulty().equals("medium")) {
            colorId = R.color.mediumColor;
        } else {
            colorId = R.color.easyColor;
        }
        holder.difficultyTV.setTextColor(ContextCompat.getColor(mContext, colorId));
        holder.estimatedTV.setText(problem.getEstimateTimeMin() + " min");
        holder.elapsedTV.setText(problem.getElapsedTimeMin() + " min");

        holder.listItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProblemDetailFragment problemDetailFragment = new ProblemDetailFragment();
                problemDetailFragment.setProblem(problem);
                problemDetailFragment.setParentActivity(mParentActivity);
                mParentActivity.pushFragment(problemDetailFragment, ProblemDetailFragment.TAG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProblemList.getProblems().size();
    }

    public static class ProblemViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout listItemLayout;
        private TextView titleTV;
        private TextView difficultyTV;
        private TextView estimatedTV;
        private TextView elapsedTV;

        public ProblemViewHolder(View itemView) {
            super(itemView);
            listItemLayout = itemView.findViewById(R.id.listItemLayout);
            titleTV = itemView.findViewById(R.id.titleTV);
            difficultyTV = itemView.findViewById(R.id.difficultyTV);
            estimatedTV = itemView.findViewById(R.id.estimatedTV);
            elapsedTV = itemView.findViewById(R.id.elapsedTV);
        }
    }
}
