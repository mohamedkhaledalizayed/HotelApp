package com.nuriden.mohamed.hotelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FilterResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_result);

        getSupportActionBar().setTitle("Recommended");
    }
}
