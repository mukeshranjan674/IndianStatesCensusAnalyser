package com.capgemini.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.sensusanalyser.CensusAnalyserException;
import com.capgemini.sensusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	private static final String STATE_CSV_DATA = "C:\\Users\\LENOVO\\eclipse-workspace\\IndianStateCensusAnalyser" + 
												 "\\src\\main\\resources\\StateCensusData.csv";
	private static final String WRONG_FILE = "C:\\Users\\LENOVO\\eclipse-workspace\\StateCensusData.txt";

	@Test
	public void whenGivenCsvPathShouldReturnNumberOfRecords() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int records;
			records = stateCensusAnalyser.loadIndiaCensusData(STATE_CSV_DATA);
			assertEquals(28, records);
		} catch (CensusAnalyserException e) {
			e.getMessage();
		}
	}

	@Test
	public void whenGivenWrongCsvFileShouldThrowCustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadIndiaCensusData(WRONG_FILE);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.NO_FILE_FOUND, e.type);
		}
	}
}
