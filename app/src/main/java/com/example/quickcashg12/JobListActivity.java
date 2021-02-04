package com.example.quickcashg12;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JobListActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private ArrayList<Job> originalJobsList;

    // this list will be set in the job adapter
    private ArrayList<Job> jobAdapterList;

    private Button filterJobsButton;
    private Button postJob_btn;
    private Button logIn_btn;
    private Button dashboard;

    private RecyclerView jobsListView;
    private JobAdapter jobAdapter;

    JobFilterProcessor filterProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        reference = FirebaseDatabase.getInstance().getReference().child("Job");
        originalJobsList = new ArrayList<>();
        jobAdapterList = new ArrayList<>();

        filterJobsButton = findViewById(R.id.filterJobsButton);
        logIn_btn = findViewById(R.id.login_btn);
        postJob_btn = findViewById(R.id.postJob_btn);

        jobAdapter = new JobAdapter(this, jobAdapterList);
        jobsListView = findViewById(R.id.jobListView);

        dashboard = findViewById(R.id.dashboard);
        //the "log in" button will open up the Log In activity
        logIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogIn_activity();
            }
        });

        //the "post job" button will open up the Post Job activity
        postJob_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPostJob_activity();
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // each child of snapshot represents one job. Add it to the search_list
                for(DataSnapshot childSnapshot : snapshot.getChildren()) {
                    // use the getValue method to retrieve a Job object
                    Job currentJob = childSnapshot.getValue(Job.class);
                    // add the name of the job to the search_list_array
                    originalJobsList.add(currentJob);
                    jobAdapterList.add(currentJob);
                }

                jobAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        filterProcessor = new JobFilterProcessor(originalJobsList);

        filterJobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildFilterDialogWindow().show();
            }
        });

        jobsListView.setAdapter(jobAdapter);
        jobsListView.setLayoutManager(new LinearLayoutManager(this));

        dashboard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard();
            }
        });
    }


    public void openLogIn_activity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openPostJob_activity(){
        Intent intent = new Intent(this, PostJob.class);
        startActivity(intent);
    }

    public AlertDialog buildFilterDialogWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View filterView = inflater.inflate(R.layout.filter_window_layout, null);

        Button finishButton = filterView.findViewById(R.id.finishButton);
        final EditText filterTermEV = filterView.findViewById(R.id.filterTermEV);
        final NumberPicker minRange = filterView.findViewById(R.id.minRange);
        minRange.setMinValue(0);
        minRange.setMaxValue(999_999_999);


        final NumberPicker maxRange = filterView.findViewById(R.id.maxRange);
        maxRange.setMinValue(0);
        maxRange.setMaxValue(999_999_999);



        final Spinner jobTypeSpinner = filterView.findViewById(R.id.jobTypeSpinner);

        // add JobTypes to the Spinner
        JobType[] jobTypes = JobType.values();
        String[] jobTypesList = new String[jobTypes.length];

        for (int jobIndex = 0; jobIndex < jobTypes.length; jobIndex++) {
            jobTypesList[jobIndex] = jobTypes[jobIndex].toString();
        };

        ArrayAdapter<String> jobListAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, jobTypesList);
        jobListAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        jobTypeSpinner.setAdapter(jobListAdapter);

        final RadioGroup rg = filterView.findViewById(R.id.filterSelectGroup);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(filterView);

        final AlertDialog alertDialog = dialogBuilder.create();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedButton = filterView.findViewById(radioGroup.getCheckedRadioButtonId());

                switch(checkedButton.getText().toString()) {
                    case "No Filter":
                        filterTermEV.setVisibility(View.GONE);
                        maxRange.setVisibility(View.GONE);
                        minRange.setVisibility(View.GONE);
                        jobTypeSpinner.setVisibility(View.GONE);
                        break;
                    case "Filter based on Job Title":
                    case "Filter based on Job Location":
                        filterTermEV.setVisibility(View.VISIBLE);
                        maxRange.setVisibility(View.GONE);
                        minRange.setVisibility(View.GONE);
                        jobTypeSpinner.setVisibility(View.GONE);
                        break;
                    case "Filter based on Salary Range":
                        maxRange.setVisibility(View.VISIBLE);
                        minRange.setVisibility(View.VISIBLE);
                        filterTermEV.setVisibility(View.GONE);
                        jobTypeSpinner.setVisibility(View.GONE);
                        break;
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the id of the checked button
                RadioButton checkedButton = filterView.findViewById(rg.getCheckedRadioButtonId());

                // perform different filtering based on teh selected filter
                switch(checkedButton.getText().toString()) {
                    case "No Filter":
                        // get the original list
                        originalJobsList = filterProcessor.getOriginalJobList();
                        break;
                    case "Filter based on Job Title":
                        // filter based on title using the Filter processor
                        String searchTerm = filterTermEV.getText().toString();

                        originalJobsList = filterProcessor.filterBasedOnTitle(searchTerm);

                        break;
                    case "Filter based on Job Location":
                        // filter based on job location
                        searchTerm = filterTermEV.getText().toString();

                        originalJobsList = filterProcessor.filterBasedOnLocation(searchTerm);
                        break;
                    case "Filter based on Salary Range":
                        // filter based on salary range

                        int minSalary = minRange.getValue();

                        int maxSalary = maxRange.getValue();

                        // perform filtering only if maxSalary is greater than minSalary
                        if(maxSalary > minSalary) {
                            originalJobsList = filterProcessor.filterBasedOnSalaryRange(minSalary, maxSalary);
                        }

                        break;
                }

//                System.out.println(originalJobsList);

                jobAdapterList.clear();

                for(Job current : originalJobsList) {
                    jobAdapterList.add(current);
                }

                jobAdapter.notifyDataSetChanged();

                alertDialog.hide();
            }
        });

        return alertDialog;
    }
    public  void openDashboard(){
        Intent intent = new Intent(this, Home_Activity.class);
        startActivity(intent);
    }
}

/* Code Review:
as in a user's view, I think all features are added, I could not come up with anything for promotion.
 */