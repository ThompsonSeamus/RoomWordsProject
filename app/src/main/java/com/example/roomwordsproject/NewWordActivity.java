package com.example.roomwordsproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordsproject.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
    }
}