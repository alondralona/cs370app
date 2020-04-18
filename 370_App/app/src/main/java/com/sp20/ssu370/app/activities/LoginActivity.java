package com.sp20.ssu370.app.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sp20.ssu370.app.R;
import com.sp20.ssu370.app.api.RetrofitClient;
import com.sp20.ssu370.app.models.LoginResponse;
import com.sp20.ssu370.app.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);


        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getInstance(this).isLoggedIn()){

            Intent intent = new Intent(this,ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid argument");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
            return;

        }
        if (password.length() < 6) {
            editTextPassword.setError("Password needs to be at least 6 characters long");
            editTextPassword.requestFocus();
            return;

        }



        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(!loginResponse.isError()){
                    //save user
                    //open profile
                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(loginResponse.getUser());

                    Intent intent = new Intent(LoginActivity.this,ProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.textViewRegister:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }

    }
}

