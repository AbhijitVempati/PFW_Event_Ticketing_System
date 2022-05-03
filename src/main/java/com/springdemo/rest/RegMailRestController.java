package com.springdemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.Registration;
import com.springdemo.service.RegistrationService;

@RestController
@RequestMapping("/api")
public class RegMailRestController {
	
	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/saveRegistration")
	public  Registration saveRegistration(@RequestBody Registration theRegistration) {
		
		theRegistration.setEvent_id(0);
		registrationService.saveRegistration(theRegistration);
		
		return theRegistration;
	}
		
	
}
