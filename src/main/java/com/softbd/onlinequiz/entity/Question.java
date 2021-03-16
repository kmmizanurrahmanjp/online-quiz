package com.softbd.onlinequiz.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class Question implements Serializable{

	private static final long serialVersionUID = -2429706486360036980L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionNo;
	private String question;
	private int mark;
	@Transient
	private Option options;
	
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public Option getOptions() {
		return options;
	}
	public void setOptions(Option options) {
		this.options = options;
	}
	
	
}
