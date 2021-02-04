package com.example.quickcashg12;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder> {

    ArrayList<Job> jobsList;
    Context context;

    public JobAdapter(Context context, ArrayList<Job> jobsList) {
        this.jobsList = jobsList;
        this.context = context;
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater jobViewInflater = LayoutInflater.from(context);
        View jobView = jobViewInflater.inflate(R.layout.job_layout, parent, false);

        return new JobHolder(jobView);
    }

    @Override
    public void onBindViewHolder(@NonNull final JobHolder holder, final int position) {
        holder.jobTitleTV.setText(jobsList.get(position).getName());
        holder.jobLocationTV.setText(jobsList.get(position).getLocation());
        holder.jobTypeTV.setText(jobsList.get(position).getJobType().toString());
        holder.salaryTV.setText("" + jobsList.get(position).getSalary());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JobDetails.class);
                intent.putExtra("jobName", jobsList.get(position).getName());
                intent.putExtra("salary", holder.salaryTV.getText().toString());
                intent.putExtra("jobLocation", jobsList.get(position).getLocation());
                intent.putExtra("jobType", jobsList.get(position).getJobType().toString());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        View container;
        TextView jobTitleTV, jobLocationTV, jobTypeTV, salaryTV;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            jobTitleTV = itemView.findViewById(R.id.jobTitleTV);
            jobLocationTV = itemView.findViewById(R.id.jobLocationTV);
            jobTypeTV = itemView.findViewById(R.id.jobTypeTV);
            salaryTV = itemView.findViewById(R.id.salaryTV);
            container = itemView;
        }
    }
}
