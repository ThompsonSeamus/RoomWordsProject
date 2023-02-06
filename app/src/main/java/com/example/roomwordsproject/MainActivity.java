package com.example.roomwordsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomwordsproject.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private WordViewModel mWordViewModel;

    public static final String WORD_ID = "word_id";
    public static final String WORD_WORD = "word_word";

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        //recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                mGetNewWord.launch(intent);
            }
        });

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Word word = adapter.getWordAtPosition(position);
                        Toast.makeText(MainActivity.this, "Deleting " + word.getWord(), Toast.LENGTH_SHORT).show();
                        mWordViewModel.deleteWord(word);
                    }
                }
        );
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private ActivityResultLauncher<Intent> mGetNewWord = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Word word = new Word(result.getData().getStringExtra(NewWordActivity.EXTRA_REPLY));
                        mWordViewModel.insert(word);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Word not saved because it is empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }

    );

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_all) {
            mWordViewModel.deleteAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateWord(Word word) {
        Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
        intent.putExtra(WORD_ID, word.getId());
        intent.putExtra(WORD_WORD, word.getWord());
        mGetNewWord.launch(intent);
    }

    private ActivityResultLauncher<Intent> getmGetNewWord = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        Word word;
                        String strWord = result.getData().getStringExtra(WORD_WORD);

                        int id = result.getData().getIntExtra(WORD_ID, -1);
                        if(id >= 0){
                            word = new Word(strWord, id);
                            mWordViewModel.updateWord(word);
                        } else{
                            word = new Word(strWord);
                            mWordViewModel.insert(word);
                        }

                    }
                }
            }

    );
}