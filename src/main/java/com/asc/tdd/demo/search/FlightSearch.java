package com.asc.tdd.demo.search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.asc.tdd.demo.vo.Flight;

public class FlightSearch {
	private List<Flight> allFlights;

	public FlightSearch(List<Flight> allFlights) {
		this.allFlights = new ArrayList<>(allFlights);
	}

	public List<Flight> selectAll() {
		return new ArrayList<>(allFlights);
	}

	public List<Flight> selectByOriginAndDestAirport(String originAirport, String destAirport) {
		List<Flight> results = new ArrayList<>();
		for(Flight flight: allFlights) {
			if(flight.getOrigin().getName().equals(originAirport)) {
				if(flight.getDestination().getName().equals(destAirport)) {
					results.add(flight);
				}
			}
		}
		return results;
	}

	public List<Flight> selectByOriginDestAirportAndDeparture(String originAirport, String destAirport, Date departure) {
		List<Flight> results = new ArrayList<>();
		List<Flight> byOriginAndDest = selectByOriginAndDestAirport(originAirport, destAirport);
		for(Flight flight: byOriginAndDest) {
			if(isSameDay(flight.getDeparture(), departure)) {
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

}
