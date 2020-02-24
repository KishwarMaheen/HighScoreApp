package com.kishwar.highscoreapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class HighScoreRepository {
    private HighScoreDAO highScoreDAO;
    private LiveData<List<HighScore>> allHighScores;

    HighScoreRepository(Application application){
        HighScoreDatabase highScoreDatabase = HighScoreDatabase.getDatabase(application);
        highScoreDAO = highScoreDatabase.highScoreDAO();
        allHighScores = highScoreDAO.getAllScores();
    }

    LiveData<List<HighScore>> getAllHighScores(){
        return allHighScores;
    }
}
