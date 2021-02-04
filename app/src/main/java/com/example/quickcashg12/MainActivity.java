package com.example.quickcashg12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText emailAddress, password;
    Button log_button,dashboard;
    private FirebaseAuth mAuth;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hooks to all xml elements in activity_main.xml
        emailAddress = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        log_button = findViewById(R.id.login_button);
        dashboard=findViewById(R.id.btn_dashb);

//        startActivity(new Intent(this, PostJob.class));
        //startActivity(new Intent(this, JobListActivity.class));

        //Login Button onClick perform
        log_button.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        dashboard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard();
            }
        });

    }

//    //opening search activity/page
//    public void openSearchActivity(){
//        Intent intent = new Intent(this, SearchActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onClick(View v){
        userLogin();
    }

    private void userLogin(){
        String email = emailAddress.getText().toString().trim();
        String pass = password.getText().toString().trim();
        InputValidator v1 = new InputValidator();
        v1.seteMail(email);
        v1.setPassword(pass);
        if(!v1.checkEmail()){
            emailAddress.setError("Please enter an email!");
            return;
        }if(!v1.checkPass()){
            password.setError("Please enter your password!");
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // go to search activity
                            Toast.makeText(MainActivity.this,"Welcome to Quick Cash community :)",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this,SearchActivity.class));
                        }else{
                            Toast.makeText(MainActivity.this,"E-mail or password is not correct!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    //Open home_activity
    public  void openDashboard(){
        Intent intent = new Intent(this, Home_Activity.class);
        startActivity(intent);
    }

    public void btn_goSignUp(View view) {
        startActivity(new Intent(getApplicationContext(),Signup_pg.class));
    }

}