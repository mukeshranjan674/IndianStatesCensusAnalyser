package com.capgemini.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.sensusanalyser.CensusAnalyserException;
import com.capgemini.sensusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/StateCensusData.csv";
	private static final String CENSUS_CSV_FILE_PATH = "./src/test/resources/CensusData.csv";

	@Test
	public void whenGivenCsvPathShouldReturnNumberOfRecords() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int records = stateCensusAnalyser.loadIndiaCensusData(STATE_CENSUS_CSV_FILE_PATH);
			assertEquals(28, records);
			System.out.println(records);
		} catch (CensusAnalyserException e) {
			e.getMessage();
		}
	}

	@Test
	public void whenGivenWrongFileShouldThrowCustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
		}
	}

	@Test
	public void whenGivenRightCsvFileButWrongTypeShouldThrowCustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			int records = stateCensusAnalyser.loadIndiaCensusData(CENSUS_CSV_FILE_PATH);
			System.out.println(records);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.WRONG_CSV_DATA, e.type);
		}
	}
}
