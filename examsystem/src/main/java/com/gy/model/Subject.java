package com.gy.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Subject implements Serializable {
	private int questionId;
	private int questionScore;
	private String questionName;
	private List<String> options;
	private Set<String> answersFlag;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuestionScore() {
		return questionScore;
	}
	public void setQuestionScore(int questionScore) {
		this.questionScore = questionScore;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public Set<String> getAnswersFlag() {
		return answersFlag;
	}
	public void setAnswersFlag(Set<String> answersFlag) {
		this.answersFlag = answersFlag;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answersFlag == null) ? 0 : answersFlag.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((questionName == null) ? 0 : questionName.hashCode());
		result = prime * result + questionScore;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (answersFlag == null) {
			if (other.answersFlag != null)
				return false;
		} else if (!answersFlag.equals(other.answersFlag))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (questionName == null) {
			if (other.questionName != null)
				return false;
		} else if (!questionName.equals(other.questionName))
			return false;
		if (questionScore != other.questionScore)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Subject [questionScore=" + questionScore + ", questionName=" + questionName + ", options=" + options
				+ ", answersFlag=" + answersFlag + "]";
	}
	public Subject(int questionScore, String questionName, List<String> options, Set<String> answersFlag) {
		super();
		this.questionScore = questionScore;
		this.questionName = questionName;
		this.options = options;
		this.answersFlag = answersFlag;
	}
	public Subject() {
		// TODO Auto-generated constructor stub
	}
	
	

}
