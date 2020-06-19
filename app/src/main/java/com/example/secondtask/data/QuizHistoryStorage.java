package com.example.secondtask.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static com.example.secondtask.data.QuizHistory.QUIZ_HISTORY_KEY;

public class QuizHistoryStorage {
    private static final String QUIZ_HISTORY_FILE = "quizHistoryFile";

    public void add(Context context, QuizScore quizScore) {
        QuizHistory quizHistory = this.getQuizHistoryObject(context);
        if(quizHistory == null) {
            quizHistory = new QuizHistory();
        }
        quizHistory.getQuizScoreList().add(quizScore);
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(QUIZ_HISTORY_KEY, new Gson().toJson(quizHistory));
        editor.apply();
    }

    public QuizHistory getQuizHistoryObject(Context context) {
        SharedPreferences sharedPreferences = getInstance(context);
        String data = sharedPreferences.getString(QUIZ_HISTORY_KEY, null);
        return data == null ? null : new Gson().fromJson(data, QuizHistory.class);
    }

    private SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences(QUIZ_HISTORY_FILE, Context.MODE_PRIVATE);
    }
}
