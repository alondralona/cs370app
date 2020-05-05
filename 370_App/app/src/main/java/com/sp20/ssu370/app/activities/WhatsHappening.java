package com.sp20.ssu370.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sp20.ssu370.app.R;

public class WhatsHappening extends AppCompatActivity {

    TextView Title, PostTitle, PostDate, Details;
    EditText TitleText, DateText, DetailsText;
    Button Cancel, Done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_happening);

        Title = findViewById(R.id.title);
        PostTitle = findViewById(R.id.post_title);
        PostDate = findViewById(R.id.post_date);
        Details = findViewById(R.id.details);

        TitleText = findViewById(R.id.title_text);
        DateText = findViewById(R.id.post_date_text);
        DetailsText = findViewById(R.id.details_text);

        Cancel = findViewById(R.id.cancel_button);

        Cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhatsHappening.this, PostAndMeets.class));
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            //@Override

            Editable postTitleText = TitleText.getText();
            Editable dateText = DateText.getText();
            Editable details = DetailsText.getText();

            public void onClick(View v) {
                if (TextUtils.isEmpty(postTitleText)) {
                    TitleText.setError("Title is required");
                }
                else if (TextUtils.isEmpty(dateText)) {
                    DateText.setError("Date is required");
                }
                else if (TextUtils.isEmpty(details)) {
                    DetailsText.setError("Details are required");
                }

                startActivity(new Intent(WhatsHappening.this, PostAndMeets.class));
            }

        });
    }
}
