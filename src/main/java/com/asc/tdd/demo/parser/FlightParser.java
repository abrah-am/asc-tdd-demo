package com.asc.tdd.demo.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightParser {
	private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");

	public List<Flight> parse(List<String> flightStrList) throws Exception {
		List<Flight> flights = new ArrayList<>();
		for(String flightStr: flightStrList) {
			String[] data = flightStr.split("\\|");
			try {
				if(data[0].startsWith("Flight#")) continue;
				Flight flight = new Flight(data[0], sdf.parse(data[1]), 
						new Airport(data[2], data[3], data[4], data[5]), 
						new Airport(data[6], data[7], data[8], data[9]));
				if(flights.contains(flight) || 
						flight.getOrigin().getCity().equals(flight.getDestination().getCity())) {
					continue;
				}
				flights.add(flight);
			} catch (ParseException e) {
				throw new Exception("Invalid value for departure: " + data[1]);
			}
		}
		return flights;
	}

}
