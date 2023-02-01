package com.example.roomwordsproject;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public void insert(Word word) { mRepository.insert(word); }

    public void deleteAll(){
        mRepository.deleteAll();
    }
}
