package com.asc.tdd.demo.search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.asc.tdd.demo.reader.FlightFileReader;
import com.asc.tdd.demo.vo.Flight;

public class FlightSearch {
	private final List<Flight> allFlights;

	public FlightSearch(List<Flight> allFlights) {
		this.allFlights = new ArrayList<>(allFlights);
	}

	public List<Flight> selectAll() {
		return new ArrayList<>(allFlights);
	}
	
	public List<Flight> searchByCriteria(Flight criteria) {
		List<Flight> results = new ArrayList<>();
		for(Flight flight: allFlights) {
			if(match(flight, criteria)) {
				results.add(flight);
			}
		}
		return results;
	}

	private static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}
	
	private static boolean match(Flight original, Flight criteria) {
		boolean match = true;
		if(criteria.getOrigin().getName() != null) {
			match &= criteria.getOrigin().getName().equals(original.getOrigin().getName());
		}
		if(criteria.getDestination().getName() != null) {
			match &= criteria.getDestination().getName().equals(original.getDestination().getName());
		}
		if(criteria.getDeparture() != null) {
			match &= isSameDay(criteria.getDeparture(), original.getDeparture());
		}
		return match;
	}
	
}
