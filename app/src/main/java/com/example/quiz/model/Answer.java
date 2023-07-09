package com.example.quiz.model;

public class Answer {
    private int id;
    private int quizId;
    private float questionId;
    private int active;
    private int correct;
    private String createdAt;
    private String updatedAt;
    private String content;

    public Answer(){

    }

    public Answer(int id, int quizId, float questionId, int active, int correct, String createdAt, String updatedAt, String content) {
        this.id = id;
        this.quizId = quizId;
        this.questionId = questionId;
        this.active = active;
        this.correct = correct;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public float getQuestionId() {
        return questionId;
    }

    public void setQuestionId(float questionId) {
        this.questionId = questionId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
