package com.capgemini.sensusanalyser;

public class CensusAnalyserException extends Exception {

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public enum ExceptionType {
		FILE_NOT_FOUND, UNABLE_TO_PARSE, NO_CENSUS_DATA, CSV_FILE_INTERNAL_ISSUES
	}

	public ExceptionType type;
}