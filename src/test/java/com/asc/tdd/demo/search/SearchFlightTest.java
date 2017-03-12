package com.asc.tdd.demo.search;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.asc.tdd.demo.mock.FlightMockData;
import com.asc.tdd.demo.vo.Flight;

public class SearchFlightTest {

	
	@Test
	public void selectAllFlightsO() {
		List<Flight> actual = new FlightSearch(FlightMockData.allFlights).selectAll();
		assertEquals(FlightMockData.allFlights.size(), actual.size());
	}
	
	
	@Test
	public void selectFlightsByOriginAndDestAirport() {
		List<Flight> actual = new FlightSearch(FlightMockData.allFlights).selectByOriginAndDestAirport("Los Angeles", "Philadelphia - Trenton/Mercer NJ");
		List<Flight> expected = Arrays.asList(FlightMockData._f00000);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);

		actual = new FlightSearch(FlightMockData.allFlights).selectByOriginAndDestAirport("Hartsfield Jackson", "Orlando - Herndon");
		expected = Arrays.asList(FlightMockData._f00001);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);

		actual = new FlightSearch(FlightMockData.allFlights).selectByOriginAndDestAirport("Los Angeles", "New York - La Guardia");
		expected = Arrays.asList(FlightMockData._f00004, FlightMockData._f00005);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectFlightsByOriginDestAiportAndDeparture() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		List<Flight> actual = new FlightSearch(FlightMockData.allFlights).selectByOriginDestAirportAndDeparture("Los Angeles", "New York - La Guardia", sdf.parse("03/24/17"));
		List<Flight> expected = Arrays.asList(FlightMockData._f00004);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);

		actual = new FlightSearch(FlightMockData.allFlights).selectByOriginDestAirportAndDeparture("Los Angeles", "New York - La Guardia", sdf.parse("03/25/17"));
		expected = Arrays.asList(FlightMockData._f00005);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);
		
		
	}
	
	

}
