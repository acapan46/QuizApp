package com.example.quiz.model;

public class Score {
    private int id;
    private String username;
    private int correct;
    private int fullScore;
    private String takenAt;

    public Score(int id,String username, int correct, int fullScore, String takenAt) {
        this.id = id;
        this.username = username;
        this.correct = correct;
        this.fullScore = fullScore;
        this.takenAt = takenAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
