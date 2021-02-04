package com.example.quickcashg12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JobDetails extends AppCompatActivity {
    TextView jobName, salary, jobLocation, jobType;
    Button applyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        jobName = (TextView) findViewById(R.id.jobTitle);
        salary = (TextView) findViewById(R.id.salary);
        jobLocation = (TextView) findViewById(R.id.jobLocation);
        jobType = (TextView) findViewById(R.id.jobType);
        applyButton = (Button) findViewById(R.id.applyButton);

        String jobNameText = (String) getIntent().getStringExtra("jobName");
        String salaryText = (String) getIntent().getStringExtra("salary");
        String jobLocationText = (String) getIntent().getStringExtra("jobLocation");
        String jobTypeText = (String) getIntent().getStringExtra("jobType");
        jobName.setText(jobNameText);
        salary.setText("$"+salaryText);
        jobLocation.setText(jobLocationText);
        jobType.setText(jobTypeText);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_applyToJobs_page();
            }
        });
    }

    public  void open_applyToJobs_page(){
        Intent intent = new Intent(this, apply_to_jobs.class);
        startActivity(intent);
    }
}