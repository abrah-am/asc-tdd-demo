package com.asc.tdd.demo.mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public final class FlightMockData {

	public static final String HEADER = "Flight#|Departure(MM-dd-yy HH:mm)|Origin Airport|Orig City|Orig State|Dest Airport|Dest City|Dest state";
	public static final String F00000 = "F00000|03-24-17 15:00|Los Angeles|Los Angeles|CA|US|Philadelphia - Trenton/Mercer NJ|Philadelphia|PA|US";
	public static final String F00001 = "F00001|04-18-17 09:00|Hartsfield Jackson|Atlanta|GA|US|Orlando - Herndon|Orlando|FL|US";
	public static final String F00002 = "F00002|04-18-17 09:00|Hartsfield Jackson|Atlanta|GA|US|Hartsfield Jackson|Atlanta|GA|US";
	public static final String F00003 = "F00003|aa-aa-17 12:00|Phoenix|Phoenix|AZ|US|New York - Kenedy|New York|NY|US";
	public static final String F00004 = "F00004|03-24-17 15:00|Los Angeles|Los Angeles|CA|US|New York - La Guardia|New York|NY|US";
	public static final String F00005 = "F00005|03-25-17 15:00|Los Angeles|Los Angeles|CA|US|New York - La Guardia|New York|NY|US";
	
	public static final Airport _losAngeles = new Airport("Los Angeles", "Los Angeles", "CA", "US");
	public static final Airport _philadelphiaTrentont = new Airport("Philadelphia - Trenton/Mercer NJ", "Philadelphia", "PA", "US");
	public static final Airport _hartsfieldJackson = new Airport("Hartsfield Jackson", "Atlanta", "GA", "US");
	public static final Airport _orlandoHerndon = new Airport("Orlando - Herndon", "Orlando", "FL", "US");
	public static final Airport _nyLaGuardia = new Airport("New York - La Guardia", "New York", "NY", "US");
	
	public static Flight _f00000;
	public static Flight _f00001;
	public static Flight _f00004;
	public static Flight _f00005;
	
	public static final List<Flight> allFlights = new ArrayList<>();
	
	static {
		final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy HH:mm");
		try {
			FlightMockData._f00000 = new Flight("F00000", sdf.parse("03-24-17 15:00"), FlightMockData._losAngeles, FlightMockData._philadelphiaTrentont);
			FlightMockData._f00001 = new Flight("F00001", sdf.parse("04-18-17 09:00"), FlightMockData._hartsfieldJackson, FlightMockData._orlandoHerndon);
			FlightMockData._f00004 = new Flight("F00004", sdf.parse("03-24-17 15:00"), FlightMockData._losAngeles, FlightMockData._nyLaGuardia);
			FlightMockData._f00005 = new Flight("F00005", sdf.parse("03-25-17 15:00"), FlightMockData._losAngeles, FlightMockData._nyLaGuardia);
			allFlights.add(_f00000);
			allFlights.add(_f00001);
			allFlights.add(_f00004);
			allFlights.add(_f00005);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
