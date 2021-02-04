package com.example.quickcashg12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class apply_to_jobs extends AppCompatActivity {

    EditText emp_name, emp_email, emp_address, emp_description;
    Button apply_btn;

    TextView apply_status;

    DatabaseReference reff;

    Applied_jobs applied_jobs;

    InputValidator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_to_jobs);

        emp_name = findViewById(R.id.employee_name);
        emp_email = findViewById(R.id.employee_email);
        emp_address = findViewById(R.id.employee_address);
        emp_description = findViewById(R.id.employee_description);
        apply_btn = findViewById(R.id.apply_button);
        apply_status = findViewById(R.id.applied_status);
        applied_jobs = new Applied_jobs();
        reff = FirebaseDatabase.getInstance().getReference().child("Applied_jobs");
        validator = new InputValidator();

        //upon pressing the applied button
        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting all the user inputted data
                String inp_name = emp_name.getText().toString().trim();
                String inp_email = emp_email.getText().toString().trim();
                String inp_address = emp_address.getText().toString().trim();
                String inp_description = emp_description.getText().toString().trim();

                validator.setUserName(inp_name);
                validator.seteMail(inp_email);
                validator.setAddress(inp_address);
                validator.setDescription(inp_description);

                if(!validator.checkAddress()) {
                    emp_address.setError("Please enter a valid address");
                }
                else if(!validator.checkName()) {
                    emp_name.setError("Please enter a valid name");
                }
                else if(!validator.checkEmail()) {
                    emp_email.setError("Please enter a valid email");
                }
                else if(!validator.checkDescription()) {
                    emp_description.setError("Please enter a valid description");
                }
                else {

                    //adding the user inputted data to the applied_jobs class to save to our firebase
                    applied_jobs.setEmp_name(inp_name);
                    applied_jobs.setEmp_email(inp_email);
                    applied_jobs.setEmp_address(inp_address);
                    applied_jobs.setEmp_description(inp_description);

                    //inserting the data to firebase
                    reff.push().setValue(applied_jobs);

                    apply_btn.setText("Applied");

                    //set the status visibility to show that user successfully applied to a job
                    apply_status.setVisibility(View.VISIBLE);
                }

//                Toast.makeText(apply_to_jobs.this, "Applied to this job successfully",Toast.LENGTH_LONG).show();
            }
        });



    }
}