package com.asc.tdd.demo.parser;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.asc.tdd.demo.mock.FlightMockData;
import com.asc.tdd.demo.vo.Airport;

public class AirportParserTest {

	@Test
	public void shouldReadStringLinesAndReturnAirportObjects() throws Exception {
		final String hartsfielJackson = "ATL|Hartsfield Jackson|Atlanta|GA|US";
		final String losAngeles = "LAX|Los Angeles|Los Angeles|CA|US";
		List<Airport> actual = new AirportParser().parse(Arrays.asList(hartsfielJackson));
		List<Airport> expected = Arrays.asList(FlightMockData._hartsfieldJackson);
		assertEquals("The number of airports expected doesn't match expected", expected.size(), actual.size());
		assertEquals(expected, actual);
		
		actual = new AirportParser().parse(Arrays.asList(hartsfielJackson, losAngeles));
		expected = Arrays.asList(FlightMockData._hartsfieldJackson, FlightMockData._losAngeles);
		assertEquals("The number of airports expected doesn't match expected", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldIgnoreTheHeader() throws Exception {
		final String header = "code|name|city|state|country";
		final String hartsfielJackson = "ATL|Hartsfield Jackson|Atlanta|GA|US";
		final String losAngeles = "LAX|Los Angeles|Los Angeles|CA|US";
		List<Airport> actual = new AirportParser().parse(Arrays.asList(header, hartsfielJackson, losAngeles));
		List<Airport> expected = Arrays.asList(FlightMockData._hartsfieldJackson, FlightMockData._losAngeles);
		assertEquals("The number of airports expected doesn't match expected", expected.size(), actual.size());
		assertEquals(expected, actual);
	}

}
