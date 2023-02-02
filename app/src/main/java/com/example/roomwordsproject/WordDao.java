package com.example.roomwordsproject;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Delete
    void deleteWord(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table ORDER BY word COLLATE NOCASE ASC")
    LiveData<List<Word>> getAllWords();

    @Update
    void update(Word... word);
}