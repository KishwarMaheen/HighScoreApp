package com.kishwar.highscoreapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class HighScoreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HighScoreViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        recyclerView = findViewById(R.id.recyclerView);
        final HighScoreListAdapter adapter = new HighScoreListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = new ViewModelProvider(this).get(HighScoreViewModel.class);
        viewModel.getAllHighScores().observe(this, new Observer<List<HighScore>>() {
            @Override
            public void onChanged(List<HighScore> highScores) {
                adapter.setHighScores(highScores);
            }
        });
    }
}
