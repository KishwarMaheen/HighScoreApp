package com.kishwar.highscoreapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HighScoreDAO {

    @Insert
    void insert(HighScore highScore);

    @Query("SELECT * from high_score_table ORDER BY score DESC")
    LiveData<List<HighScore>> getAllScores();

    @Query("DELETE from high_score_table")
    void deleteAll();
}
