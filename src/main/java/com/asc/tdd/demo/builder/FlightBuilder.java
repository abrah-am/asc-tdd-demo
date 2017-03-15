package com.asc.tdd.demo.builder;

import java.util.Date;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightBuilder {
	private String originAirportName;
	private String destAirportName;
	private Date departure;
	
	public FlightBuilder fromAirport(String originAirportName){
		this.originAirportName = originAirportName;
		return this;
	}
	public FlightBuilder toAirport(String destAirportName){
		this.destAirportName = destAirportName;
		return this;
	}
	public FlightBuilder departingOn(Date departure) {
		this.departure = departure;
		return this;
	}
	public Flight build() {
		return new Flight(null, departure, 
				new Airport(null, originAirportName, null, null, null), 
				new Airport(null, destAirportName, null, null, null));
	}
	
}