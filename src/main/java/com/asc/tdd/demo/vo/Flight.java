package com.asc.tdd.demo.vo;

import java.util.Date;

public class Flight {
	private final String number;
	private final Date departure;
	private final Airport origin;
	private final Airport destination;
	public Flight(String number, Date departure, Airport origin, Airport destination) {
		super();
		this.number = number;
		this.departure = departure;
		this.origin = origin;
		this.destination = destination;
	}
	public String getNumber() {
		return number;
	}
	public Date getDeparture() {
		return departure;
	}
	public Airport getOrigin() {
		return origin;
	}
	public Airport getDestination() {
		return destination;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (departure == null) {
			if (other.departure != null)
				return false;
		} else if (!departure.equals(other.departure))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Flight [number=" + number + ", departure=" + departure + ", origin=" + origin + ", destination="
				+ destination + "]";
	}
	
	

}
