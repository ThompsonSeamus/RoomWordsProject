package com.example.roomwordsproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordsproject.REPLY";

    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        //Text watcher
        TextInputLayout inputLayout = findViewById(R.id.textInputLayout);
        EditText editWordView = inputLayout.getEditText();
        Button saveButton = findViewById(R.id.save_button);

        //intent tings
        String strWord = getIntent().getStringExtra(MainActivity.WORD_WORD);
        int id = getIntent().getIntExtra(MainActivity.WORD_ID, -1);


        if(strWord != ""){
            editWordView.setText(strWord);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(editWordView.getText().toString())){
                    setResult(RESULT_CANCELED, replyIntent);
                    Toast.makeText(NewWordActivity.this, EXTRA_REPLY, Toast.LENGTH_SHORT).show();
                } else{
                    String word = editWordView.getText().toString();
                    replyIntent.putExtra(MainActivity.WORD_WORD, word);
                    replyIntent.putExtra(MainActivity.WORD_ID, id);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

    }
}