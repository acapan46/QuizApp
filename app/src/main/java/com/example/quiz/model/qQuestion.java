package com.example.quiz.model;

import java.util.Date;

public class qQuestion {

    private int id;
    private float qid;
    private int  quizId;
    private String type;
    private int active;
    private int score;
    private String createdAt;
    private String updatedAt;
    private String content;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correct;

    public qQuestion(){

    }

    public qQuestion(int id, float qid, int quizId, String type, int active,int score, String createdAt, String updatedAt, String content, String option1, String option2, String option3, String option4, String correct) {
        this.id = id;
        this.qid = qid;
        this.quizId = quizId;
        this.type = type;
        this.active = active;
        this.score = score;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correct = correct;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQid() {
        return qid;
    }

    public void setQid(float qid) {
        this.qid = qid;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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
