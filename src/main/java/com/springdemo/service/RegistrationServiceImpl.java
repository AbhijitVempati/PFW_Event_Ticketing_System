package com.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springdemo.dao.RegistrationDAO;
import com.springdemo.entity.Registration;
import com.springdemo.util.JavaMailUtil;

@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	private RegistrationDAO registrationDAO;
	
	@Override
	@Transactional
	public void saveRegistration(Registration theRegistration) {
		registrationDAO.saveRegistration(theRegistration);
		
	//	Registration registration = new Registration();
		
		try {
			JavaMailUtil.sendMail(theRegistration.getStudent_email());
			//System.out.println("testing");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
