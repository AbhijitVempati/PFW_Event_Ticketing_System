package com.springdemo.service;

import java.util.List;

import com.springdemo.entity.Events;



public interface EventService {

	public List<Events> getEvents();

	public void saveEvent(Events theEvent);

	public Events getEvent(String eventDate);

	//public void deleteCustomer(int theId);
	
}