package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.Events;

public interface EventsDAO {
	
	public List<Events> getEvents();

	//public void saveEvent(Events theEvent);

	public Events getEvent(String eventDate);

	public void saveEvent(Events theEvent);

	//public void deleteCustomer(int theId);

}
