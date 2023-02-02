package com.example.roomwordsproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordsproject.REPLY";

    public boolean isSavedWord;
    public int id;
    public String[] Words;
    public int arrayLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        //Text watcher
        TextInputLayout inputLayout = findViewById(R.id.textInputLayout);
        EditText editWordView = inputLayout.getEditText();
        Button saveButton = findViewById(R.id.save_button);
        Button cancelButton = findViewById(R.id.cancel_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(editWordView.getText().toString())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    String word = editWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, new Intent());
            }
        });

    }
}