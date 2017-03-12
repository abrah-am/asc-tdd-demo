package com.asc.tdd.demo.parser;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightParserTest {

	private static final String HEADER = "Flight#|Departure(MM-dd-yy HH:mm)|Origin Airport|Orig City|Orig State|Dest Airport|Dest City|Dest state";
	private static final String F00000 = "F00000|03-24-17 15:00|Los Angeles|Los Angeles|CA|US|Philadelphia - Trenton/Mercer NJ|Philadelphia|PA|US";
	private static final String F00001 = "F00001|04-18-17 09:00|Hartsfield Jackson|Atlanta|GA|US|Orlando - Herndon|Orlando|FL|US";
	private static final String F00002 = "F00002|04-18-17 09:00|Hartsfield Jackson|Atlanta|GA|US|Hartsfield Jackson|Atlanta|GA|US";
	private static final String F00003 = "F00003|aa-aa-17 12:00|Phoenix|Phoenix|AZ|US|New York - Kenedy|New York|NY|US";
	
	private static final Airport _losAngeles = new Airport("Los Angeles", "Los Angeles", "CA", "US");
	private static final Airport _philadelphiaTrentont = new Airport("Philadelphia - Trenton/Mercer NJ", "Philadelphia", "PA", "US");
	private static final Airport _hartsfieldJackson = new Airport("Hartsfield Jackson", "Atlanta", "GA", "US");
	private static final Airport _orlandoHerndon = new Airport("Orlando - Herndon", "Orlando", "FL", "US");
	
	private static Flight _f00000;
	private static Flight _f00001;
	
	static {
		final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");
		try {
			_f00000 = new Flight("F00000", sdf.parse("03-24-17 15:00"), _losAngeles, _philadelphiaTrentont);
			_f00001 = new Flight("F00001", sdf.parse("04-18-17 09:00"), _hartsfieldJackson, _orlandoHerndon);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected=Exception.class)
	public void shouldThrowAnExceptionWhenDepartureDateIsMalformatted() throws Exception {
		new FlightParser().parse(Arrays.asList(F00000, F00003));
		
	}
	
	@Test
	public void shouldReadStringLinesAndReturnAFlightList() throws Exception {
		List<Flight> actual = new FlightParser().parse(Arrays.asList(F00000, F00001));
		List<Flight> expected = Arrays.asList(_f00000, _f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldIgnoreFlightsWhichOriginAndDestinationIsSameAirport() throws Exception { 
		List<Flight> actual = new FlightParser().parse(Arrays.asList(F00000, F00001, F00002));
		List<Flight> expected = Arrays.asList(_f00000, _f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldIgnoreFileHeader() throws Exception {
		List<Flight> actual = new FlightParser().parse(Arrays.asList(HEADER, F00000, F00001));
		List<Flight> expected = Arrays.asList(_f00000, _f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}

	@Test
	public void shouldIgnoreDuplicatedFlights() throws Exception {
		List<Flight> actual = new FlightParser().parse(Arrays.asList(F00000, F00001, F00000));
		List<Flight> expected = Arrays.asList(_f00000, _f00001);
		assertEquals("Invalid number of Flights returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}
}
