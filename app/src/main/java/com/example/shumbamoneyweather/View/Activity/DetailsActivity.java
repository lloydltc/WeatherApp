package com.example.shumbamoneyweather.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shumbamoneyweather.R;

public class DetailsActivity extends AppCompatActivity {
    TextView txtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txtv = findViewById(R.id.textView);
        Intent i = getIntent();
        String  frdate = i.getStringExtra("date");
        txtv.setText(frdate);
    }
}