package com.asc.tdd.demo.search;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.asc.tdd.demo.parser.AirportParserTest;
import com.asc.tdd.demo.vo.Airport;

public class SearchAirportTest {
	private AirportSearch airportSearch;

	@Before
	public void init() { 
		airportSearch = new AirportSearch(AirportParserTest.allAirports);
	}
	
	@Test
	public void searchAirportByCode() {
		List<Airport> actual = airportSearch.searchByAirportCode("TTN");
		List<Airport> expected = Arrays.asList(AirportParserTest._philadelphiaTrentont);
		assertEquals("Invalid number of Airports returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
		
		actual = airportSearch.searchByAirportCode("ORL");
		expected = Arrays.asList(AirportParserTest._orlandoHerndon);
		assertEquals("Invalid number of Airports returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
	}

	@Test
	public void shouldReturnAnEmptyListWhenAirportIsNotFound() {
		List<Airport> actual = airportSearch.searchByAirportCode("YYY");
		List<Airport> expected = Arrays.asList();
		assertEquals("Invalid number of Airports returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void shouldReturnAllValidAirports() {
		List<Airport> actual = airportSearch.selectAll();
		List<Airport> expected = AirportParserTest.allAirports;
		assertEquals("Invalid number of Airports returned: ", expected.size(), actual.size());
		assertEquals(expected, actual);
		
	}
}
