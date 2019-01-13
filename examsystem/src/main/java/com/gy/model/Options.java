package com.gy.model;

public class Options {
    private Integer optionsId;

    private String optionsDesc;

    private String optionsFlag;

    private Integer questionId;

    public Integer getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(Integer optionsId) {
        this.optionsId = optionsId;
    }

    public String getOptionsDesc() {
        return optionsDesc;
    }

    public void setOptionsDesc(String optionsDesc) {
        this.optionsDesc = optionsDesc;
    }

    public String getOptionsFlag() {
        return optionsFlag;
    }

    public void setOptionsFlag(String optionsFlag) {
        this.optionsFlag = optionsFlag;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

	@Override
	public String toString() {
		return "Options [optionsId=" + optionsId + ", optionsDesc=" + optionsDesc + ", optionsFlag=" + optionsFlag
				+ ", questionId=" + questionId + "]";
	}
    
}