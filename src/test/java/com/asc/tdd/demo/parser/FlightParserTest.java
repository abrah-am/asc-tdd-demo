package com.asc.tdd.demo.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.asc.tdd.demo.mock.FlightMockData;
import com.asc.tdd.demo.search.AirportSearch;
import com.asc.tdd.demo.vo.Flight;

public class FlightParserTest {
	private final AirportSearch airportSearch = new AirportSearch(AirportParserTest.allAirports);
	private final FlightParser flightParser = new FlightParser(airportSearch);

	
	@Test (expected=Exception.class)
	public void shouldThrowAnExceptionWhenDepartureDateIsMalformatted() throws Exception {
		new FlightParser(airportSearch).parse(Arrays.asList(FlightMockData.F00000, FlightMockData.F00003));
	}
	
	@Test
	public void shouldReadStringLinesAndReturnAFlightList() throws Exception {
		List<Flight> actual = flightParser.parse(Arrays.asList(FlightMockData.F00000, FlightMockData.F00001));
		List<Flight> expected = Arrays.asList(FlightMockData._f00000, FlightMockData._f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldIgnoreFlightsWhichOriginAndDestinationIsSameCity() throws Exception { 
		List<Flight> actual = flightParser.parse(Arrays.asList(FlightMockData.F00000, FlightMockData.F00001, FlightMockData.F00002));
		List<Flight> expected = Arrays.asList(FlightMockData._f00000, FlightMockData._f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldIgnoreFileHeader() throws Exception {
		List<Flight> actual = flightParser.parse(Arrays.asList(FlightMockData.HEADER, FlightMockData.F00000, FlightMockData.F00001));
		List<Flight> expected = Arrays.asList(FlightMockData._f00000, FlightMockData._f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}

	@Test
	public void shouldIgnoreDuplicatedFlights() throws Exception {
		List<Flight> actual = flightParser.parse(Arrays.asList(FlightMockData.F00000, FlightMockData.F00001, FlightMockData.F00000));
		List<Flight> expected = Arrays.asList(FlightMockData._f00000, FlightMockData._f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
}
