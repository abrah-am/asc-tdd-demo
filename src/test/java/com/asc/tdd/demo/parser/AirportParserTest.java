package com.asc.tdd.demo.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class AirportParserTest {

	private static final String AIRPORT_HEADER = "code|name|city|state|country";
	private static final String LOS_ANGELES = "LAX|Los Angeles|Los Angeles|CA|US";
	private static final String HARTSFIELD_JACKSON = "ATL|Hartsfield Jackson|Atlanta|GA|US";
	
	
	public static final Airport _losAngeles = new Airport("LAX", "Los Angeles", "Los Angeles", "CA", "US");
	public static final Airport _philadelphiaTrentont = new Airport("TTN", "Philadelphia - Trenton/Mercer NJ", "Philadelphia", "PA", "US");
	public static final Airport _hartsfieldJackson = new Airport("ATL", "Hartsfield Jackson", "Atlanta", "GA", "US");
	public static final Airport _orlandoHerndon = new Airport("ORL", "Orlando - Herndon", "Orlando", "FL", "US");
	public static final Airport _nyLaGuardia = new Airport("LGA", "New York - La Guardia", "New York", "NY", "US");
	public static final List<Flight> allFlights = new ArrayList<>();

	@Test
	public void shouldReadStringLinesAndReturnAirportObjects() throws Exception {
		List<Airport> actual = new AirportParser().parse(Arrays.asList(HARTSFIELD_JACKSON));
		List<Airport> expected = Arrays.asList(AirportParserTest._hartsfieldJackson);
		assertEquals("The number of airports expected doesn't match expected", expected.size(), actual.size());
		assertEquals(expected, actual);
		
		actual = new AirportParser().parse(Arrays.asList(HARTSFIELD_JACKSON, LOS_ANGELES));
		expected = Arrays.asList(AirportParserTest._hartsfieldJackson, AirportParserTest._losAngeles);
		assertEquals("The number of airports expected doesn't match expected", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldIgnoreTheHeader() throws Exception {
		List<Airport> actual = new AirportParser().parse(Arrays.asList(AIRPORT_HEADER, HARTSFIELD_JACKSON, LOS_ANGELES));
		List<Airport> expected = Arrays.asList(AirportParserTest._hartsfieldJackson, AirportParserTest._losAngeles);
		assertEquals("The number of airports expected doesn't match expected", expected.size(), actual.size());
		assertEquals(expected, actual);
	}

}
