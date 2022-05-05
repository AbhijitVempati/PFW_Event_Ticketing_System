package com.springdemo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.springdemo.entity.Users;

public class UsersDAOImpl implements UsersDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void regUser(Users theUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.save(theUser);
		
	}

}
