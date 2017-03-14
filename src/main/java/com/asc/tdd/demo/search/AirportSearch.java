package com.asc.tdd.demo.search;

import java.util.ArrayList;
import java.util.List;

import com.asc.tdd.demo.vo.Airport;

public class AirportSearch {
	private final List<Airport> allAirports;
	
	public AirportSearch(List<Airport> allAirports) {
		this.allAirports = new ArrayList<>(allAirports);
	}

	public List<Airport> searchByAirportCode(String code) {
		List<Airport> results = new ArrayList<>();
		for(Airport airport: allAirports) {
			if(airport.getCode().equals(code)) {
				results.add(airport);
			}
		}
		return results;
	}

	public List<Airport> selectAll() {
		return new ArrayList<>(allAirports);
	}
	
}
