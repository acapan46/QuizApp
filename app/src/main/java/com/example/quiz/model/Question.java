package com.example.quiz.model;

import java.util.Date;

public class Question {

    private int id;
    private float qid;
    private int quizId;
    private String type;
    private int active;
    private String createdAt;
    private String updatedAt;
    private String content;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctAnswerIndex;

    public Question(String question, String option1, String option2, String option3, String option4, int correctAnswerIndex) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
