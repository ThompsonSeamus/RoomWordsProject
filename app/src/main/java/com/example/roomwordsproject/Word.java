package com.example.roomwordsproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;}

    @Ignore
    public Word(@NonNull String word, int id){
        this.mWord = word;
        this.id = id;
    }

    public String getWord(){return this.mWord;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

