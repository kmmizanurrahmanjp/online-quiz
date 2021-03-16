package com.softbd.onlinequiz.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Timer implements Serializable{

	private static final long serialVersionUID = -2429706486360036980L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int totalTime;	
	private int eachQuestionTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public int getEachQuestionTime() {
		return eachQuestionTime;
	}
	public void setEachQuestionTime(int eachQuestionTime) {
		this.eachQuestionTime = eachQuestionTime;
	}
	
	
	
}
