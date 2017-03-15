package com.asc.tdd.demo.builder;

import java.util.Date;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightBuilder {
	private String originCode;
	private String destCode;
	private String originAirportName;
	private String destAirportName;
	private Date departure;
	
	public FlightBuilder from(String originCode) {
		this.originCode = originCode;
		return this;
	}
	
	public FlightBuilder to(String destCode) {
		this.destCode = destCode;
		return this;
	}
	
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
				new Airport(originCode, originAirportName, null, null, null), 
				new Airport(destCode, destAirportName, null, null, null));
	}
	
}