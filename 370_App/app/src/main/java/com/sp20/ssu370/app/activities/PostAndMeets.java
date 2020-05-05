package com.sp20.ssu370.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.sp20.ssu370.app.R;

public class PostAndMeets extends AppCompatActivity {

    TextView MainTitle, PostTitle, PostText;
    DatePicker PostDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_and_meets);

        MainTitle = findViewById(R.id.title);
        PostTitle = findViewById(R.id.post_title);
        PostDate = findViewById(R.id.post_date);
        PostText = findViewById(R.id.Post_text);
    }
}
