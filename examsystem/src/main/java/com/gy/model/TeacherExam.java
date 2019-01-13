package com.gy.model;

public class TeacherExam {
    private Integer techerExamId;

    private Integer questionId;

    private String sysUserName;

    private Integer type;

    private Integer questionType;

    public Integer getTecherExamId() {
        return techerExamId;
    }

    public void setTecherExamId(Integer techerExamId) {
        this.techerExamId = techerExamId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }
}