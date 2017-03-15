package com.asc.tdd.demo.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.asc.tdd.demo.search.AirportSearch;
import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightParser implements Parser<Flight> {
	private final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");
	private final AirportSearch airportSearch;
	
	public FlightParser(AirportSearch airportSearch) {
		super();
		this.airportSearch = airportSearch;
	}



	/* (non-Javadoc)
	 * @see com.asc.tdd.demo.parser.Parser#parse(java.util.List)
	 */
	@Override
	public List<Flight> parse(List<String> flightStrList) throws Exception {
		List<Flight> flights = new ArrayList<>();
		for(String flightStr: flightStrList) {
			String[] data = flightStr.split("\\|");
			try {
				if(data[0].startsWith("Flight#")) continue;
				List<Airport> origin = airportSearch.searchByAirportCode(data[2]); 
				if(origin.isEmpty()) {
					throw new Exception("Airpot not found for code: " + data[2]);
				}
				List<Airport> destination = airportSearch.searchByAirportCode(data[3]);
				if(destination.isEmpty()) {
					throw new Exception("Airpot not found for code: " + data[3]);
				}
				
				Flight flight = new Flight(data[0], sdf.parse(data[1]), origin.get(0), destination.get(0));
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
