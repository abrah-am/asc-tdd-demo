package com.asc.tdd.demo.parser;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightParserTest {

	private static final String F00000 = "F00000|03-24-17 15:00|Los Angeles|Los Angeles|CA|US|Philadelphia - Trenton/Mercer NJ|Philadelphia|PA|US";
	
	private static final Airport _losAngeles = new Airport("Los Angeles", "Los Angeles", "CA", "US");
	private static final Airport _philadelphiaTrentont = new Airport("Philadelphia - Trenton/Mercer NJ", "Philadelphia", "PA", "US");
	
	private static Flight _f00000;
	
	static {
		final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");
		try {
			_f00000 = new Flight("F00000", sdf.parse("03-24-17 15:00"), _losAngeles, _philadelphiaTrentont);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void shouldReadAStringLineAndReturnAFlight() throws Exception {
		Flight actual = new FlightParser().parse(F00000);
		assertEquals(_f00000, actual);
	}

}
