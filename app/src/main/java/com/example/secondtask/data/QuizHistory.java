package com.example.secondtask.data;

import java.util.ArrayList;
import java.util.List;

public class QuizHistory {
    public static String QUIZ_HISTORY_KEY = "quiz_history";

    private List<QuizScore> quizScoreList = new ArrayList<>();

    public List<QuizScore> getQuizScoreList() {
        return quizScoreList;
    }

    public void setQuizScoreList(List<QuizScore> quizScoreList) {
        this.quizScoreList = quizScoreList;
    }
}
