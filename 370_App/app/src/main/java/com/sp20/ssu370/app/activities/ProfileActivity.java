package com.sp20.ssu370.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sp20.ssu370.app.R;
import com.sp20.ssu370.app.fragments.HomeFragment;
import com.sp20.ssu370.app.fragments.MapFragment;
import com.sp20.ssu370.app.fragments.MessageFragment;
import com.sp20.ssu370.app.fragments.ProfileFragment;
import com.sp20.ssu370.app.models.User;
import com.sp20.ssu370.app.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {

    private TextView textView;
    BottomNavigationView bottomNav;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = findViewById(R.id.textView);
        User user = SharedPrefManager.getInstance(this).getUser();

        textView.setText("Welcome back " + user.getName());

        //Reference bottom navigation view
        bottomNav = findViewById(R.id.bottom_navigation);

        //set first fragment to home when app opens to the main activity
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
//be able to switch in between fragments
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;

                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;

                    case R.id.nav_message:
                        selectedFragment = new MessageFragment();
                        break;

                    case R.id.nav_map:
                        selectedFragment = new MapFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });

        // reference toolbar in activity_profile
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.inflateMenu(R.menu.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("PawChat");
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);

    }
    //creating the menu at the top
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){

            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }
    }




}