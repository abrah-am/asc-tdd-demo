package com.asc.tdd.demo.search;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.asc.tdd.demo.builder.FlightBuilder;
import com.asc.tdd.demo.mock.FlightMockData;
import com.asc.tdd.demo.vo.Flight;

public class SearchFlightTest {
	private FlightSearch flightSearch;

	@Before
	public void init() { 
		flightSearch = new FlightSearch(FlightMockData.allFlights);
	}
	
	@Test
	public void selectAllFlightsO() {
		List<Flight> actual = new FlightSearch(FlightMockData.allFlights).selectAll();
		assertEquals(FlightMockData.allFlights.size(), actual.size());
	}
	
	
	@Test
	public void selectFlightsByOriginAndDestAirport() throws Exception {
		Flight criteria = new FlightBuilder()
				.fromAirport("Los Angeles").toAirport("Philadelphia - Trenton/Mercer NJ").build();
		List<Flight> actual = flightSearch.searchByCriteria(criteria);
		List<Flight> expected = Arrays.asList(FlightMockData._f00000);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);

		criteria = new FlightBuilder()
				.fromAirport("Hartsfield Jackson").toAirport("Orlando - Herndon").build();
		actual = flightSearch.searchByCriteria(criteria);
		expected = Arrays.asList(FlightMockData._f00001);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);

		criteria = new FlightBuilder()
				.fromAirport("Los Angeles").toAirport("New York - La Guardia").build();
		actual = flightSearch.searchByCriteria(criteria);
		expected = Arrays.asList(FlightMockData._f00004, FlightMockData._f00005);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void selectFlightsByOriginDestAiportAndDeparture() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		Flight criteria = new FlightBuilder()
				.fromAirport("Los Angeles").toAirport("New York - La Guardia").departingOn(sdf.parse("03/24/17")).build();
		List<Flight> actual = flightSearch.searchByCriteria(criteria);
		List<Flight> expected = Arrays.asList(FlightMockData._f00004);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);

		criteria = new FlightBuilder()
				.fromAirport("Los Angeles").toAirport("New York - La Guardia").departingOn(sdf.parse("03/25/17")).build();
		actual = flightSearch.searchByCriteria(criteria);
		expected = Arrays.asList(FlightMockData._f00005);
		assertEquals("Invalid number of results: ", expected.size(), actual.size());
		assertEquals(expected, actual);
		
		
	}
	
	

}
