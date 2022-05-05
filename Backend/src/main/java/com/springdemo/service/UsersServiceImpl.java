package com.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.UsersDAO;
import com.springdemo.entity.Users;

public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDAO usersDAO;

	@Override
	@Transactional
	public void regUser(Users theUser) {
		
		usersDAO.regUser(theUser);
		// TODO Auto-generated method stub
		
	}

}
