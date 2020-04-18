package com.sp20.ssu370.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sp20.ssu370.app.models.DefaultResponse;
import com.sp20.ssu370.app.R;
import com.sp20.ssu370.app.api.RetrofitClient;
import com.sp20.ssu370.app.storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextEmail, editTextPassword, editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);

        findViewById(R.id.buttonRegister).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);


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


    private void userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
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
        if (name.isEmpty()) {
            editTextName.setError("Name Required");
            editTextName.requestFocus();
            return;

        }



        /*User Registration using the api call*/
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, password, name);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {
                    DefaultResponse dr = response.body();
                    Toast.makeText(MainActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                }else if (response.code() == 422){
                    Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });

    }
    @Override
    public void onClick (View v){
        switch ((v.getId())) {
            case R.id.buttonRegister:
                userSignUp();
                break;

            case R.id.textViewLogin:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }

    }

}
