package com.kishwar.highscoreapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HighScoreViewModel extends AndroidViewModel {
    private HighScoreRepository highScoreRepository;
    private LiveData<List<HighScore>> allHighScores;
    public HighScoreViewModel(@NonNull Application application) {
        super(application);
        highScoreRepository = new HighScoreRepository(application);
        allHighScores = highScoreRepository.getAllHighScores();
    }
    LiveData<List<HighScore>> getAllHighScores(){
        return allHighScores;
    }
}
