package com.sp20.ssu370.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void logout(View view){
        //Logout for user in app
        FirebaseAuth.getInstance().signOut();
        //Send back to Login
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}

