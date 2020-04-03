package com.sp20.ssu370.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button map_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map_button = findViewById(R.id.button_map_text);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navIntent = new Intent(MainActivity.this, GoogleMap.class);
                startActivity(navIntent);
            }
        };

        map_button.setOnClickListener(listener);

    }
    public void logout(View view){
        //Logout for user in app
        FirebaseAuth.getInstance().signOut();
        //Send back to Login
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }




}

