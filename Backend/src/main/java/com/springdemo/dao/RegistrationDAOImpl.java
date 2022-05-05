package com.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Events;
import com.springdemo.entity.Registration;

@Repository
public class RegistrationDAOImpl implements RegistrationDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveRegistration(Registration theRegistration) {
		
		Session currentSession = sessionFactory.getCurrentSession();		
		currentSession.save(theRegistration);
		
	}
	public List<Registration> getRegistration(){
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query  ... sort by last name
		Query<Registration> theQuery = 
				currentSession.createQuery("from Registration",
						Registration.class);
		
		// execute query and get result list
		List<Registration> registration = theQuery.getResultList();
				
		// return the results		
		return registration;
	}

}
