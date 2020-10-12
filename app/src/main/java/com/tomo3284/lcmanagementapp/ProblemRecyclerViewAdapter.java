package com.tomo3284.lcmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomo3284.lcmanagementapp.Models.Problem;
import com.tomo3284.lcmanagementapp.Models.ProblemList;

public class ProblemRecyclerViewAdapter extends RecyclerView.Adapter<ProblemRecyclerViewAdapter.ProblemViewHolder> {

    private Context mContext;
    private ProblemList mProblemList;

    public ProblemRecyclerViewAdapter(Context context, ProblemList problemList) {
        mContext = context;
        mProblemList = problemList;
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
    }

    @Override
    public int getItemCount() {
        return mProblemList.getProblems().size();
    }

    public static class ProblemViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTV;

        public ProblemViewHolder(View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
        }
    }
}
