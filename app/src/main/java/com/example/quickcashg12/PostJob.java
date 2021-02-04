package com.example.quickcashg12;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostJob extends AppCompatActivity {
    Spinner jobTypeDropDown;
    EditText jobNameInput, jobLocationInput, salaryInput;
    Button postJobButton;

    DatabaseReference db_reff;

    Job job;

    boolean isJobValid = true;

    static JobType[] jobTypes;
    static String[] jobTypesList;

//    static final String[] jobTypes = {"Lawn Mower", "Bartender", "Chauffeur", "App Developer",
//           "Car Mechanic", "Sales Associate", "Software Engineer", "Web Developer", "Teacher", "Waiter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        //initiate variables
        jobTypeDropDown = (Spinner) findViewById(R.id.jobTypeDropDown);
        jobNameInput = (EditText) findViewById(R.id.jobTitleEditText);
        jobLocationInput = (EditText) findViewById(R.id.jobLocationEditText);
        salaryInput = (EditText) findViewById(R.id.salaryEditText);
        postJobButton = findViewById(R.id.postJobButton);

        int totalJobTypes = JobType.values().length;

        jobTypes = JobType.values();
        jobTypesList = new String[totalJobTypes];


        for (int jobIndex = 0; jobIndex < totalJobTypes; jobIndex++) {
            jobTypesList[jobIndex] = jobTypes[jobIndex].toString();
        }

        job = new Job();

        db_reff = FirebaseDatabase.getInstance().getReference().child("Job");
        
        postJobButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                JobInputValidator inputValidator = new JobInputValidator();
                if(!inputValidator.validateJobTitle(jobNameInput.getText().toString())) {
                    isJobValid = false;
                    jobNameInput.setError("Please enter a valid job name");
                }
                if(!inputValidator.validateJobLocation(jobLocationInput.getText().toString())) {
                    isJobValid = false;
                    jobLocationInput.setError("Please enter a valid location");
                }
                if(!inputValidator.validateJobSalary(salaryInput.getText().toString())){
                    isJobValid = false;
                    salaryInput.setError("Please enter a valid salary");
                }

                if(isJobValid)
                postJob();
            }
        });

        addJobList();
    }

    /**
     * Method adds job list to the adapter
     */
    protected void addJobList() {
        @SuppressLint("ResourceType")
//        ArrayAdapter<String> jobListAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, jobTypes);
        ArrayAdapter<String> jobListAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, jobTypesList);

        jobListAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        jobTypeDropDown.setAdapter(jobListAdapter);

    }

    /**
     * Method posts the job for employees
     */
    public void postJob() {
        // perform the job post task here
        int salary = Integer.parseInt(salaryInput.getText().toString().trim());
        JobType jobType = JobType.valueOf(jobTypeDropDown.getSelectedItem().toString().toUpperCase());
        job.setName(jobNameInput.getText().toString().trim());
        job.setJobType(jobType);
        job.setLocation(jobLocationInput.getText().toString().trim());
        job.setSalary(salary);

        db_reff.push().setValue(job);
        findViewById(R.id.jobPostStatus).setVisibility(View.VISIBLE);
    }
}