package com.capgemini.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.sensusanalyser.CensusAnalyserException;
import com.capgemini.sensusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/StateCensusData.csv";
	private static final String CENSUS_CSV_FILE_PATH = "./src/test/resources/WrongCensusData.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/WrongDelimiterData.csv";

	@Test
	public void givenCsvPath_ShouldReturn_NumberOfRecords() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(STATE_CENSUS_CSV_FILE_PATH);
			assertEquals(28, numOfRecords);
			System.out.println(numOfRecords);
		} catch (CensusAnalyserException e) {
			e.getMessage();
		}
	}

	@Test
	public void givenWrongFile_ShouldThrow_CustomException() {
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
	public void givenRightCsvFile_ButWrongType_ShouldThrow_CustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CENSUS_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}
	
	  @Test
	    public void givenWrongDelimiter_InIndiaCensusData_ShouldThrow_CustomException() {
	        try {
	        	StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
	            int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
	        } catch (CensusAnalyserException e) {
	            assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
	        }
	    }
	  
	  @Test
	    public void givenMissingHeader_InIndiaCensusData_ShouldThrow_CustomException() {
	        try {
	        	StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
	            int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
	        } catch (CensusAnalyserException e) {
	            assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
	        }
	    }
}
