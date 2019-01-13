package com.gy.model;

public class Question {
    private Integer questionId;

    private String questionName;

    private Integer questionScore;

    private Integer questuonType;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }

    public Integer getQuestuonType() {
        return questuonType;
    }

    public void setQuestuonType(Integer questuonType) {
        this.questuonType = questuonType;
    }
}