package com.example.secondtask.data;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public class QuizQuestion {
    private UUID id = UUID.randomUUID();
    private String question;
    private List<String> options;
    private String correctAnswerNumber;
    private String userAnswerNumber;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswerNumber() {
        return correctAnswerNumber;
    }

    public void setCorrectAnswerNumber(String correctAnswerNumber) {
        this.correctAnswerNumber = correctAnswerNumber;
    }

    public String getUserAnswerNumber() {
        return userAnswerNumber;
    }

    public void setUserAnswerNumber(String userAnswerNumber) {
        this.userAnswerNumber = userAnswerNumber;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof QuizQuestion) {
            return this.id.equals(((QuizQuestion) obj).getId());
        }
        return false;
    }
}
