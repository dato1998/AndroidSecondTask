package com.example.secondtask.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class QuizStorage {
    private static final String QUIZ_STORAGE_FILE = "quizStorageFile";

    public void add(Context context, QuizQuestion quizQuestion) {
        Quiz quiz = this.getQuizObject(context);
        if(quiz == null) {
            quiz = new Quiz();
        }
        quiz.getQuestions().add(quizQuestion);
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Quiz.QUIZ_STORAGE_KEY, new Gson().toJson(quiz));
        editor.apply();
    }

    public Quiz getQuizObject(Context context) {
        SharedPreferences sharedPreferences = getInstance(context);
        String data = sharedPreferences.getString(Quiz.QUIZ_STORAGE_KEY, null);
        return data == null ? null : new Gson().fromJson(data, Quiz.class);
    }

    public void delete(Context context, QuizQuestion quizQuestion) {
        Quiz quiz = this.getQuizObject(context);
        quiz.getQuestions().remove(quizQuestion);
        SharedPreferences sharedPreferences = getInstance(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Quiz.QUIZ_STORAGE_KEY, new Gson().toJson(quiz));
        editor.apply();
    }

    private SharedPreferences getInstance(Context context) {
        return context.getSharedPreferences(QUIZ_STORAGE_FILE, Context.MODE_PRIVATE);
    }
}
