package com.example.pfw_ets_prj;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.*;
import java.io.*;
import java.lang.String;

public class Event {
    public static ArrayList<Event> eventsList;

    public static ArrayList<Event> eventsForDate(LocalDate date)
    {
        ArrayList<Event> events = new ArrayList<>();

	/*if(date.isEqual(LocalDate.now()))
        {
            Event temp1 = new Event("PFW Homecoming", LocalDate.now(), LocalTime.now());
                    events.add(temp1);
	        Event temp2 = new Event("PFW vs UIC Basketball", LocalDate.now(), LocalTime.now());
                    events.add(temp2);
            return events;
        }

*/

        for(Event event : eventsList)
        {
            if(event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }


    private String name;
    private LocalDate date;
    private String time;
    private String venue;
    private String regFlag;


    public Event(String name, LocalDate date, String time, String venue, String regFlag)
    {
        this.name = name;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.regFlag = regFlag;

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public String getDateString()
    {
        return date.toString();
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getVenue()
    {
        return venue;
    }

    public void setVenue(String venue)
    {
        this.venue = venue;
    }

    public String getRegFlag()
    {
        return regFlag;
    }

    public void setRegFlag(String venue)
    {
        this.venue = regFlag;
    }



}