package com.kishwar.highscoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void seeHighScores(View view) {
        Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
        startActivity(intent);
    }

    public void exit(View view) {
        onBackPressed();
    }
}
