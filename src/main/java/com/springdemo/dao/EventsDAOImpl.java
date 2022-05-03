package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;
import com.springdemo.entity.Events;

@Repository
public class EventsDAOImpl implements EventsDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Events> getEvents() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Events> theQuery = 
				currentSession.createQuery("from Events",
											Events.class);
		
		// execute query and get result list
		List<Events> events = theQuery.getResultList();
				
		// return the results		
		return events;
	}
	
	@Override
	public Events getEvent(String eventDate) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Events theEvent= currentSession.get(Events.class, eventDate);
		
		return theEvent;
	}

	@Override
	public void saveEvent(Events theEvent) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(theEvent);
	}
	
}
