package com.sp20.ssu370.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    EditText mFullName,mEmail, mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName   = findViewById(R.id.fullName);
        mEmail      = findViewById(R.id.Email);
        mPassword   = findViewById(R.id.password);
        mPhone      = findViewById(R.id.phone);
        mRegisterBtn= findViewById(R.id.registerBtn);
        mLoginBtn   = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        //Check if user already has an account, if so direct to main activity
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }



        //Register user to firebase. Once user adds data, Register button clicked verify the data(validation)
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert text to string and trim data
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //validate data, errors
                if(TextUtils.isEmpty(email)){
                    //if true user did not enter a value in email fields
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty((password))){
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length() < 6 ){
                    mPassword.setError("Password must be  >= 6 characters");
                    return;
                }

                //Progress Bar, now show it progress of registration
                progressBar.setVisibility(View.VISIBLE);

                //Register user in firebase, add listener. Firebase object instance, pass two arguments. Add a listener to see if registration is successful or not
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if task is successful we have successfully created the user, if not we have an error
                        if(task.isSuccessful()){
                            //Display message and send user to main activity
                            Toast.makeText(Register.this,"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            //Error message showed to user
                            Toast.makeText(Register.this,"Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //Removed spinner so it doesn't show up the entire time when there is an error(User Authentication)
                            progressBar.setVisibility(View.GONE);


                        }
                    }
                });


            }
        });



        //Already logged in button

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}
