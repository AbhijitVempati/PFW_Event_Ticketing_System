package com.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.entity.Events;
import com.springdemo.service.EventService;
import com.springdemo.util.JavaMailUtil;

@RestController
@RequestMapping("/api")
public class EventRestController {
	
	//autowire the Event Service
	@Autowired
	private EventService eventService;
	
	
	//add maapping for get events 
	@GetMapping("/events")
	public List<Events> getEvents(){
		
		return eventService.getEvents();
	}
	
	@GetMapping("/events/{eventDate}")
	public Events getEvent(@PathVariable String eventDate){
		
		return eventService.getEvent(eventDate);
	}
	
	@PostMapping("/saveEvent")
	public Events addEvent(@RequestBody Events theEvent) {
		
		theEvent.setEvent_id(0);
		eventService.saveEvent(theEvent);
		
		return theEvent;
	}

}
