package com.example.roomwordsproject;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    //constructor
    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = (LiveData<List<Word>>) mWordDao.getAllWords();
    }

    //returns words on live data and executes on separate threads
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word){
        Executors.newSingleThreadExecutor().execute(new InsertRunnable(word));
    }

    private class InsertRunnable implements Runnable {
        Word word;
        public InsertRunnable(Word word) {
            this.word = word;
        }

        @Override
        public void run() {
            mWordDao.insert(word);
        }
    }
}
