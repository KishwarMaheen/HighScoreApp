package com.kishwar.highscoreapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "high_score_table")
public class HighScore {

    public int getId() {
        return id;
    }

    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;
    @NonNull
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="score")
    private int score;

    public HighScore(int id, @NonNull String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
