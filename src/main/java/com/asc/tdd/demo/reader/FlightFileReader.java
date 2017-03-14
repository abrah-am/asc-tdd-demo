package com.asc.tdd.demo.reader;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.asc.tdd.demo.parser.Parser;

public class FlightFileReader {
	
	public static <T> List<T> read(String fileName, Parser<T> parser) throws Exception {
		ClassLoader classLoader = FlightFileReader.class.getClassLoader();
		URL url = classLoader.getResource(fileName);
		Path path = Paths.get(url.toURI());
		List<String> lines = Files.readAllLines(path);
		return parser.parse(lines);
	}

}
