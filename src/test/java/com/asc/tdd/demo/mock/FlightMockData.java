package com.asc.tdd.demo.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.asc.tdd.demo.parser.AirportParserTest;
import com.asc.tdd.demo.vo.Flight;

public final class FlightMockData {

	public static final String HEADER = "Flight#|Departure Date/Time|Origin|Destination";
	public static final String F00000 = "F00000|03-24-17 15:00|LAX|TTN";
	public static final String F00001 = "F00001|04-18-17 09:00|ATL|ORL";
	public static final String F00002 = "F00002|04-18-17 09:00|ATL|ATL";
	public static final String F00003 = "F00003|aa-aa-17 12:00|PHX|JFK";
	public static final String F00004 = "F00004|03-24-17 15:00|LAX|LGA";
	public static final String F00005 = "F00005|03-25-17 15:00|LAX|LGA";
	
	public static Flight _f00000;
	public static Flight _f00001;
	public static Flight _f00004;
	public static Flight _f00005;
	public static Flight _f00006;
	public static final List<Flight> allFlights = new ArrayList<>();
	
	static {
		final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");
		try {
			FlightMockData._f00000 = new Flight("F00000", sdf.parse("03-24-17 15:00"), AirportParserTest._losAngeles, AirportParserTest._philadelphiaTrentont);
			FlightMockData._f00001 = new Flight("F00001", sdf.parse("04-18-17 09:00"), AirportParserTest._hartsfieldJackson, AirportParserTest._orlandoHerndon);
			FlightMockData._f00004 = new Flight("F00004", sdf.parse("03-24-17 15:00"), AirportParserTest._losAngeles, AirportParserTest._nyLaGuardia);
			FlightMockData._f00005 = new Flight("F00005", sdf.parse("03-25-17 15:00"), AirportParserTest._losAngeles, AirportParserTest._nyLaGuardia);
			FlightMockData._f00006 = new Flight("F00006", sdf.parse("03-25-17 15:00"), AirportParserTest._losAngeles, AirportParserTest._nyAllAirports);
			FlightMockData.allFlights.add(_f00000);
			FlightMockData.allFlights.add(_f00001);
			FlightMockData.allFlights.add(_f00004);
			FlightMockData.allFlights.add(_f00005);
			FlightMockData.allFlights.add(_f00006);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	
}
