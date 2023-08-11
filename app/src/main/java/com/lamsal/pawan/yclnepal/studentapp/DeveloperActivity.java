package com.lamsal.pawan.yclnepal.studentapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lamsal.pawan.yclnepal.R;

public class DeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        getSupportActionBar().hide();
    }
}