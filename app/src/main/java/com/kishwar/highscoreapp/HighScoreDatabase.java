package com.kishwar.highscoreapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Database(entities = {HighScore.class}, version = 1, exportSchema = false)
public abstract class HighScoreDatabase extends RoomDatabase {
    public abstract HighScoreDAO highScoreDAO();
    private static HighScoreDatabase INSTANCE;
    private static Callback roomDatabaseCallback =
            new Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    static HighScoreDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (HighScoreDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HighScoreDatabase.class, "high_score_database").
                            fallbackToDestructiveMigration().addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final HighScoreDAO mDao;
        private String JSON_STRING;

        PopulateDbAsync(HighScoreDatabase db) {
            mDao = db.highScoreDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();
            try {
                /*For personal reasons, not specifying my IP address in GitHub. Will change once
                I host online.*/
                URL url = new URL("http://yoururlhere/retrieve.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String initialJSONString;
                while ((initialJSONString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(initialJSONString);
                }
                JSON_STRING = stringBuilder.toString().trim();
                Log.d("JSON: ", JSON_STRING);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                JSONObject jsonObject = new JSONObject(JSON_STRING);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                for (int i = 1; i <= jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i - 1);
                    HighScore highScore = new HighScore(i, object.getString("name"),
                            Integer.parseInt(object.getString("score")));
                    mDao.insert(highScore);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
