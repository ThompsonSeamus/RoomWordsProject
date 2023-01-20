package com.example.roomwordsproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
    }
}
