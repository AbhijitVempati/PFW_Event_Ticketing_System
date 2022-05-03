package com.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="events")

public class Events {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_id")
	private int event_id;
	
	@Column(name="event_name")
	private String event_name;
	
	@Column(name="dept")
	private String dept;
	
	@Column(name="event_date")
	private String event_date;
	
	@Column(name="event_reg")
	private boolean event_reg;
	

	public boolean isEvent_reg() {
		return event_reg;
	}

	public void setEvent_reg(boolean event_reg) {
		this.event_reg = event_reg;
	}

	public Events() {
		
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	@Override
	public String toString() {
		return "Events [event_id=" + event_id + ", event_name=" + event_name + ", dept=" + dept + ", event_date="
				+ event_date + "]";
	}
	

}
