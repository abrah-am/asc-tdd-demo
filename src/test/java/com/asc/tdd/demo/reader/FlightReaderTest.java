package com.asc.tdd.demo.reader;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.asc.tdd.demo.builder.FlightBuilder;
import com.asc.tdd.demo.parser.AirportParser;
import com.asc.tdd.demo.parser.FlightParser;
import com.asc.tdd.demo.search.AirportSearch;
import com.asc.tdd.demo.search.FlightSearch;
import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightReaderTest {
	private AirportSearch airportSearch;
	private FlightSearch flightSearch;
	
	@Before
	public void init() throws Exception {
		List<Airport> airports = FlightFileReader.read("phase-2/airport-codes.txt", new AirportParser());
		this.airportSearch = new AirportSearch(airports);
		List<Flight> flights = FlightFileReader.read("phase-2/list-of-flights.txt", new FlightParser(airportSearch));
		this.flightSearch = new FlightSearch(flights);
		
	}
	
	@Test
	public void selectAllAirports() throws Exception {
		int expectedAirports = 29;
		int actualAirports = airportSearch.selectAll().size();
		assertEquals(expectedAirports, actualAirports);
	}
	
	@Test
	public void shouldReadAllValidFlightsFromFile() throws Exception {
		assertEquals(167, flightSearch.selectAll().size());
		
	}
	
	@Test
	public void searchFlightsByOriginAndDestinationAirport() throws Exception {
		//From: Chicago - O'Hare To: Charlotte
		Flight criteria = new FlightBuilder().fromAirport("Chicago - O'Hare").toAirport("Charlotte").build();
		int flightCount = flightSearch.searchByCriteria(criteria).size();
		assertEquals(1, flightCount);

		criteria = new FlightBuilder().fromAirport("New York - Kenedy").toAirport("Miami - International").build();
		flightCount = flightSearch.searchByCriteria(criteria).size(); 
		assertEquals(1, flightCount);

		criteria = new FlightBuilder().fromAirport("New York - La Guardia").toAirport("Hartsfield Jackson").build();
		flightCount = flightSearch.searchByCriteria(criteria).size(); 
		assertEquals(2, flightCount);

		criteria = new FlightBuilder().fromAirport("Tampa").toAirport("Boston - International").build();
		flightCount = flightSearch.searchByCriteria(criteria).size(); 
		assertEquals(0, flightCount);

		criteria = new FlightBuilder().fromAirport("Washington - All airports").toAirport("Los Angeles").build();
		flightCount = flightSearch.searchByCriteria(criteria).size(); 
		assertEquals(1, flightCount);

		criteria = new FlightBuilder().fromAirport("Chicago - Meigs").toAirport("Huston - All airports").build();
		flightCount = flightSearch.searchByCriteria(criteria).size(); 
		assertEquals(1, flightCount);

		criteria = new FlightBuilder().fromAirport("Los Angeles").toAirport("Charlotte").build();
		flightCount = flightSearch.searchByCriteria(criteria).size(); 
		assertEquals(0, flightCount);
	}	

	@Test
	public void searchFlightByOriginDestinationAndDeparture() throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//From: New York - La Guardia To: Hartsfield Jackson Departure: 04/24/2017
		Flight criteria = new FlightBuilder()
				.fromAirport("New York - La Guardia").toAirport("Hartsfield Jackson").departingOn(sdf.parse("04/24/2017")).build();
		int flightCount = flightSearch.searchByCriteria(criteria).size();
		assertEquals(1, flightCount);		

		//From: Washington - National To: Hartsfield Jackson Departure: 04/25/2017
		criteria = new FlightBuilder()
				.fromAirport("Washington - National").toAirport("Hartsfield Jackson").departingOn(sdf.parse("04/25/2017")).build();
		flightCount = flightSearch.searchByCriteria(criteria).size();
		assertEquals(1, flightCount);
		
		//From: Charlotte To: Huston - All airports Departure: 04/01/2017
		criteria = new FlightBuilder()
				.fromAirport("Charlotte").toAirport("Huston - All airports").departingOn(sdf.parse("04/01/2017")).build();
		flightCount = flightSearch.searchByCriteria(criteria).size();
		assertEquals(1, flightCount);	

		
		//From: Chicago - Meigs To: Seattle - Lake Union Departure: 03/20/2017
		criteria = new FlightBuilder()
				.fromAirport("Chicago - Meigs").toAirport("Seattle - Lake Union").departingOn(sdf.parse("03/20/2017")).build();
		flightCount = flightSearch.searchByCriteria(criteria).size();
		assertEquals(1, flightCount);			

		//From: San Francisco To: Boston Departure: 03/15/2017
		criteria = new FlightBuilder()
				.fromAirport("San Francisco").toAirport("Boston").departingOn(sdf.parse("03/15/2017")).build();
		flightCount = flightSearch.searchByCriteria(criteria).size();
		assertEquals(1, flightCount);			
		
		//From: Chicago - Meigs To: Huston - All airports Departure 04/20/2017
		criteria = new FlightBuilder()
				.fromAirport("Chicago - Meigs").toAirport("Huston - All airports").departingOn(sdf.parse("04/21/2017")).build();
		flightCount = flightSearch.searchByCriteria(criteria).size();
		assertEquals(1, flightCount);		
	}
	
}
