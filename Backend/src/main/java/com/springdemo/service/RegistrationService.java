package com.springdemo.service;

import java.util.List;

import com.springdemo.entity.Registration;

public interface RegistrationService {
	
	public void saveRegistration(Registration theRegistration);
	public List<Registration> getRegistration();

}
