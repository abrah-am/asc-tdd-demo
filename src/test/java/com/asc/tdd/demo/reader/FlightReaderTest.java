package com.asc.tdd.demo.reader;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.asc.tdd.demo.parser.FlightParser;
import com.asc.tdd.demo.vo.Flight;

public class FlightReaderTest {

	@Test
	public void shouldReadAllValidFlightsFromFile() throws Exception {
		List<Flight> flights = FlightFileReader.read("phase-1/list-of-flights.txt", new FlightParser());
		assertEquals(167, flights.size());
		
	}

}
