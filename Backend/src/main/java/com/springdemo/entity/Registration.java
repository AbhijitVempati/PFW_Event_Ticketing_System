package com.springdemo.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registration")
public class Registration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reg_id")
	private int reg_id;
	
	@Column(name="student_id")
	private int student_id;
	
	@Column(name="event_id")
	private int event_id;
	
	@Column(name="event_name")
	private String event_name;
	
	@Column(name="event_date")
	private Date event_date;
	
	@Column(name="event_time")
	private Time event_time;
	
	@Column(name="student_first")
	private String student_first;
	
	@Column(name="student_last")
	private String student_last;
	
	@Column(name="student_email")
	private String student_email;
	
	public int getReg_id() {
		return reg_id;
	}



	public void setReg_id(int reg_id) {
		this.reg_id = reg_id;
	}



	public int getStudent_id() {
		return student_id;
	}



	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}



	public int getEvent_id() {
		return event_id;
	}



	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}



	public String getEvent_name() {
		return event_name;
	}



	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}



	public Date getEvent_date() {
		return event_date;
	}



	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}



	public Time getEvent_time() {
		return event_time;
	}



	public void setEvent_time(Time event_time) {
		this.event_time = event_time;
	}



	public String getStudent_first() {
		return student_first;
	}



	public void setStudent_first(String student_first) {
		this.student_first = student_first;
	}



	public String getStudent_last() {
		return student_last;
	}



	public void setStudent_last(String student_last) {
		this.student_last = student_last;
	}



	public String getStudent_email() {
		return student_email;
	}



	public void setStudent_email(String student_email) {
		this.student_email = student_email;
	}



	
	
	public Registration() {
		
	}



	@Override
	public String toString() {
		return "Registration [reg_id=" + reg_id + ", student_id=" + student_id + ", event_id=" + event_id
				+ ", event_name=" + event_name + ", event_date=" + event_date + ", event_time=" + event_time
				+ ", student_first=" + student_first + ", student_last=" + student_last + ", student_email="
				+ student_email + "]";
	}

	

	
}
