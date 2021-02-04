package com.example.quickcashg12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home_Activity extends AppCompatActivity {

    private Button signUp_btn, JoblistActivity_btn, userLoginActivity_btn, payButton,search,postJobs_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        signUp_btn = findViewById(R.id.signUp_pg_btn);
        JoblistActivity_btn = findViewById(R.id.JobListActivity_btn);
        userLoginActivity_btn = findViewById(R.id.UserLoginActivity_btn);
        postJobs_btn = findViewById(R.id.PostJob_btn);
        payButton = findViewById(R.id.Btn_pay);
        search = findViewById(R.id.Btn_search);
        //Click to open SignUp Page
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUp_page();
            }
        });

        //Click to open Job list activity page (where Employee filters out jobs)
        JoblistActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openJobListActivity_page();
            }
        });

        //Click to open User Login page
        userLoginActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserLoginActivity_page();
            }
        });

        //Click to open employer post jobs page
        postJobs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPostJobs_page();
            }
        });

        //Click to open pay page
        payButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPayActivity();
            }
        });

        //Open search page
        search.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearchActivity();
            }
        });

    }

    public  void openSignUp_page(){
        Intent intent = new Intent(this, Signup_pg.class);
        startActivity(intent);
    }

    public  void openJobListActivity_page(){
        Intent intent = new Intent(this, JobListActivity.class);
        startActivity(intent);
    }


    public  void openPostJobs_page(){
        Intent intent = new Intent(this, PostJob.class);
        startActivity(intent);
    }


    public  void openUserLoginActivity_page(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public  void openPayActivity(){
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
    }
    public  void openSearchActivity(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
