package com.example.secondtask.data;

import java.util.ArrayList;

public class Quiz {
    public static String QUIZ_STORAGE_KEY = "quiz_storage";

    private ArrayList<QuizQuestion> questions = new ArrayList<>();

    public ArrayList<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuizQuestion> questions) {
        this.questions = questions;
    }

}
