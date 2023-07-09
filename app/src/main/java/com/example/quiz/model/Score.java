package com.example.quiz.model;

public class Score {
    private String username;
    private int correct;
    private int fullScore;
    private String takenAt;

    public Score(String username, int correct, int fullScore, String takenAt) {
        this.username = username;
        this.correct = correct;
        this.fullScore = fullScore;
        this.takenAt = takenAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getFullScore() {
        return fullScore;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public String getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(String takenAt) {
        this.takenAt = takenAt;
    }
}
