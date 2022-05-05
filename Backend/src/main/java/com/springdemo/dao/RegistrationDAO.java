package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.Events;
import com.springdemo.entity.Registration;

public interface RegistrationDAO {

	public void saveRegistration(Registration theRegistration);
	public List<Registration> getRegistration();
}
