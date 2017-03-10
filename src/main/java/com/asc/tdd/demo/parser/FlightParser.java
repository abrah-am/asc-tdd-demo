package com.asc.tdd.demo.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightParser {
	private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");

	public Flight parse(String flightStr) throws Exception {
		String[] data = flightStr.split("\\|");
		try {
			return new Flight(data[0], sdf.parse(data[1]), 
					new Airport(data[2], data[3], data[4], data[5]), 
					new Airport(data[6], data[7], data[8], data[9]));
		} catch (ParseException e) {
			throw new Exception("Invalid value for departure: " + data[1]);
		}
	}

}
