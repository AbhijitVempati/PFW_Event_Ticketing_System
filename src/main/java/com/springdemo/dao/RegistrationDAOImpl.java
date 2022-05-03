package com.springdemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
