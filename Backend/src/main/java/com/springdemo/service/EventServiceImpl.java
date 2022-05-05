package com.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.dao.EventsDAO;
import com.springdemo.entity.Customer;
import com.springdemo.entity.Events;

@Service
public class EventServiceImpl implements EventService {

	// need to inject customer dao
	@Autowired
	private EventsDAO eventsDAO;

	@Override
	@Transactional
	public List<Events> getEvents() {
		return eventsDAO.getEvents();
	}
	
	@Override
	@Transactional
	public Events getEvent(String eventDate) {
		return eventsDAO.getEvent(eventDate);
	}
	
	@Override
	@Transactional
	public void saveEvent(Events theEvent) {
		
		eventsDAO.saveEvent(theEvent);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public void saveCustomer(Customer theCustomer) {
	 * 
	 * customerDAO.saveCustomer(theCustomer); }
	 * 
	 * @Override
	 * 
	 * @Transactional public Customer getCustomer(int theId) {
	 * 
	 * return customerDAO.getCustomer(theId); }
	 * 
	 * @Override
	 * 
	 * @Transactional public void deleteCustomer(int theId) {
	 * 
	 * customerDAO.deleteCustomer(theId); }
	 */
}
