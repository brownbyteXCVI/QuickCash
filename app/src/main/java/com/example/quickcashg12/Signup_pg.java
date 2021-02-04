package com.example.quickcashg12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_pg extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button start;
    private EditText name, email, password, creditCardNum, PIN, securityCode;
    private TextView signUp_btn,back_btn;
    private RadioButton employer, employee;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
//    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_pg);
        mAuth = FirebaseAuth.getInstance();          //implement firebase here;

        signUp_btn = (Button) findViewById(R.id.signupFinish);
        signUp_btn.setOnClickListener(this);

        back_btn = (Button) findViewById(R.id.btn_home);
        back_btn.setOnClickListener(this);

        name = (EditText) findViewById(R.id.signupname);
        email = (EditText) findViewById(R.id.signupemail);
        password = (EditText) findViewById(R.id.signupPass);
        creditCardNum = (EditText) findViewById(R.id.creditNum);
        PIN = (EditText) findViewById(R.id.PIN);
        securityCode = (EditText) findViewById(R.id.seucrityCode);

        employee = (RadioButton) findViewById(R.id.employee);
        employer = (RadioButton) findViewById(R.id.employer);

//        bar = (ProgressBar) findViewById(R.id.progressBar);
    }


    public void btn_main(View view) {
        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_home:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.signupFinish:
                registerUser();
                break;
        }
    }

    public String userType(){
        String result = "";
        if(employee.isChecked()){
            result = "employee";
        }if(employer.isChecked()){
            result = "employer";
        }
        return result;
    }

    private  void registerUser(){
        final String thisEmail = email.getText().toString().trim();
        final String thisName = name.getText().toString().trim();
        final String userType = this.userType();
        final String thisCreditcardNum = creditCardNum.getText().toString().trim();
        final String thisPIN = PIN.getText().toString().trim();
        final String thisSecurityCode = securityCode.getText().toString().trim();
        String thisPass = password.getText().toString().trim();

        // validate input
        InputValidator v1 = new InputValidator();
        v1.seteMail(thisEmail);
        v1.setPassword(thisPass);
        v1.setUserName(thisName);
        v1.setCreditCardNum(thisCreditcardNum);
        v1.setPin(thisPIN);
        v1.setSecurityCode(thisSecurityCode);
        //check each Edittext
        if (!v1.checkName()){
            name.setError("Full name is required!");
            name.requestFocus();
            return;
        }

        if(userType.isEmpty()){
            employee.setError("Select one");
            name.requestFocus();
            return;
        }

        if(!v1.checkEmail()){
            email.setError("Input a valid email address!");
            email.requestFocus();
            return;
        }
        if(!v1.checkPass()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if(!v1.passLength()){
            password.setError("Enter a password at least 6 digits!");
            password.requestFocus();
            return;
        }
        if (!v1.creditcardLength() || !v1.isDigit(v1.getCreditCardNum())){
            creditCardNum.setError("Invalid Credit Card Number! Enter a 16-digit number");
            creditCardNum.requestFocus();
            return;
        }
        if (!v1.PinLength() || !v1.isDigit(v1.getPin())){
            PIN.setError("Invalid PIN! Enter a 4-digit or 6-digit number");
            PIN.requestFocus();
            return;
        }
        if (!v1.securityCodeLength() || !v1.isDigit(v1.getSecurityCode())){
            securityCode.setError("Invalid Security Code! Enter a 3-digit number");
            securityCode.requestFocus();
            return;
        }

//        bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(thisEmail,thisPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(thisName,thisEmail,userType,thisCreditcardNum, thisPIN, thisSecurityCode);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Signup_pg.this,"Register completed", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                                                bar.setVisibility(View.VISIBLE);
                                            }else{
                                                Toast.makeText(Signup_pg.this,"ERROR: failed to register!", Toast.LENGTH_LONG).show();
//                                                bar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(Signup_pg.this,"ERROR: failed to register!", Toast.LENGTH_LONG).show();
//                            bar.setVisibility(View.GONE);
                        }
                    }
                });


    }

}