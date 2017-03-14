package com.asc.tdd.demo.reader;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.asc.tdd.demo.parser.FlightParser;
import com.asc.tdd.demo.search.FlightSearch;
import com.asc.tdd.demo.vo.Flight;

public class FlightReaderTest {

	@Test
	public void shouldReadAllValidFlightsFromFile() throws Exception {
		List<Flight> flights = FlightFileReader.<Flight>read("phase-1/list-of-flights.txt", new FlightParser());
		FlightSearch search = new FlightSearch(flights);
		assertEquals(167, search.selectAll().size());
		
	}

}
