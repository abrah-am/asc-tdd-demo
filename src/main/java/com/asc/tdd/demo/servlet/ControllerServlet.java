package com.asc.tdd.demo.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asc.tdd.demo.builder.FlightCriteriaBuilder;
import com.asc.tdd.demo.parser.AirportParser;
import com.asc.tdd.demo.parser.FlightParser;
import com.asc.tdd.demo.reader.FileParser;
import com.asc.tdd.demo.search.AirportSearch;
import com.asc.tdd.demo.search.FlightSearch;
import com.asc.tdd.demo.vo.Airport;
import com.asc.tdd.demo.vo.Flight;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/index.html"})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightSearch flightSearch;
	private AirportSearch airportSearch;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() throws Exception {
        super();
    	List<Airport> allAirports = FileParser.read("phase-2/airport-codes.txt", new AirportParser());
    	airportSearch = new AirportSearch(allAirports);
    	List<Flight> allFlights = FileParser.read("phase-2/list-of-flights.txt", new FlightParser(airportSearch));
    	flightSearch = new FlightSearch(allFlights);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		request.setAttribute("from", from);
		
		String to = request.getParameter("to");
		request.setAttribute("to", to);
		
		String departure = request.getParameter("departure");
		request.setAttribute("departure", departure);
		
		String fromNearby = request.getParameter("fromNearby");
		request.setAttribute("fromNearby", fromNearby);

		String toNearby = request.getParameter("toNearby");
		request.setAttribute("toNearby", toNearby);
		
		FlightCriteriaBuilder builder = new FlightCriteriaBuilder();
		builder = addOrigin(from, fromNearby, builder);
		builder = addDestination(to, toNearby, builder);
		builder = addDeparture(departure, builder);
		
		request.setAttribute("airports", airportSearch.selectAll());
		request.setAttribute("flights", flightSearch.searchByCriteria(builder.build()));
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	private FlightCriteriaBuilder addOrigin(String from, String fromNearby, FlightCriteriaBuilder builder) throws ServletException {
		if(from != null && !from.isEmpty()) {
			if(fromNearby != null && !fromNearby.isEmpty()) {
				builder = builder.fromCity(getCityForAirportCode(from));
			}
			else{
				builder = builder.from(from);
			}
		}
		return builder;
	}

	private String getCityForAirportCode(String from) throws ServletException {
		List<Airport> origin = airportSearch.searchByAirportCode(from);
		if(origin.isEmpty()) {
			throw new ServletException("Unable to find airport for code: " + from);
		}
		return origin.get(0).getCity();
	}

	
	private FlightCriteriaBuilder addDestination(String to, String toNearby, FlightCriteriaBuilder builder)
			throws ServletException {
		if(to != null && !to.isEmpty()) {
			if(toNearby != null && !toNearby.isEmpty()) {
				builder = builder.toCity(getCityForAirportCode(to));
			}
			else{
				builder = builder.to(to);
			}
		}
		return builder;
	}

	private FlightCriteriaBuilder addDeparture(String departure, FlightCriteriaBuilder builder) throws ServletException {
		if(departure != null && !departure.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				builder = builder.departingOn(sdf.parse(departure));
			} catch (ParseException e) {
				throw new ServletException("Unable to parse: " + departure);
			}
		}
		return builder;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
