package com.kishwar.highscoreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HighScoreListAdapter extends RecyclerView.Adapter<HighScoreListAdapter.HighScoreViewHolder> {
    private final LayoutInflater inflater;
    private List<HighScore> highScores;

    HighScoreListAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HighScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new HighScoreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HighScoreViewHolder holder, int position) {
        if(highScores!=null){
            HighScore current = highScores.get(position);
            holder.nameView.setText(current.getName());
            holder.scoreView.setText(Integer.toString(current.getScore()));
        }
    }

    @Override
    public int getItemCount() {
        return highScores!=null ? highScores.size() : 0;
    }

    void setHighScores(List<HighScore> highScores){
        this.highScores = highScores;
        notifyDataSetChanged();
    }

    class HighScoreViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameView;
        private final TextView scoreView;
        public HighScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            scoreView = itemView.findViewById(R.id.score);
        }
    }
}
