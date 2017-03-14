package com.asc.tdd.demo.parser;

import java.util.List;

public interface Parser<T> {

	List<T> parse(List<String> lines) throws Exception;

}