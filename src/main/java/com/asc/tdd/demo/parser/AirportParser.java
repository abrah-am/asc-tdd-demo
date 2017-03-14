package com.asc.tdd.demo.parser;

import java.util.ArrayList;
import java.util.List;

import com.asc.tdd.demo.vo.Airport;

public class AirportParser implements Parser<Airport> {

	@Override
	public List<Airport> parse(List<String> lines) throws Exception {
		List<Airport> airports = new ArrayList<>();
		for(String line: lines) {
			String[] data = line.split("\\|");
			if(data[0].equals("code")) continue;
			airports.add(new Airport(data[0], data[1], data[2], data[3], data[4]));
		}
		return airports;
	}

}
