package com.asc.tdd.demo.builder;

import java.util.Date;

import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

public class FlightCriteriaBuilder {
	private String originCity;
	private String destCity;
	private String originCode;
	private String destCode;
	private String originAirportName;
	private String destAirportName;
	private Date departure;
	
	public FlightCriteriaBuilder from(String originCode) {
		this.originCode = originCode;
		return this;
	}
	public FlightCriteriaBuilder to(String destCode) {
		this.destCode = destCode;
		return this;
	}
	public FlightCriteriaBuilder fromCity(String originCity) {
		this.originCity = originCity;
		return this;
	}
	public FlightCriteriaBuilder toCity(String destCity) {
		this.destCity = destCity;
		return this;
	}
	public FlightCriteriaBuilder fromAirport(String originAirportName){
		this.originAirportName = originAirportName;
		return this;
	}
	public FlightCriteriaBuilder toAirport(String destAirportName){
		this.destAirportName = destAirportName;
		return this;
	}
	public FlightCriteriaBuilder departingOn(Date departure) {
		this.departure = departure;
		return this;
	}
	public Flight build() {
		return new Flight(null, departure, 
				new Airport(originCode, originAirportName, originCity, null, null), 
				new Airport(destCode, destAirportName, destCity, null, null));
	}
	
}