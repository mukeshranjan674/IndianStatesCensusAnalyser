package com.capgemini.sensusanalyser;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new CSVBuilder();
	}

}
