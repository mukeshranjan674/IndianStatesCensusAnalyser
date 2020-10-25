package com.capgemini.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.sensusanalyser.CensusAnalyserException;
import com.capgemini.sensusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_DATA = "./src/test/resources/StateCensusData.csv";
	private static final String STATE_CODE_DATA = "./src/test/resources/StateCodeData.csv";
	private static final String CSV_WRONG_DATA = "./src/test/resources/WrongData.csv";
	private static final String WRONG_FILE_PATH = "./src/main/resources/StateCensusData.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/CensusWrongDelimiterData.csv";
	private static final String INDIAN_CENSUS_CSV_HEADER_MISSING = "./src/test/resources/CensusHeaderMissing.csv";
	private static final String STATE_CODE_CSV_WRONG_DELIMITER = "./src/test/resources/CodeWrongDelimiter.csv";
	private static final String STATE_CODE_CSV_HEADER_MISSING = "./src/test/resources/CodeHeaderMissing.csv";

	@Test
	public void givenCsvPath_ShouldReturn_NumberOfRecords() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(STATE_CENSUS_DATA);
			assertEquals(28, numOfRecords);
			System.out.println(numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenWrongFile_ShouldThrow_CustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
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
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CSV_WRONG_DATA);
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
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_HEADER_MISSING);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenCsvPath_InStateCodeData_ShouldReturn_NumberOfRecords() {

		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_DATA);
			assertEquals(38, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenWrongCsvPath_InStateCodeData_ShouldThrow_CustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			stateCensusAnalyser.loadIndianStateCode(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
		}
	}

	@Test
	public void givenRightStateCsvFile_ButWrongType_InStateCodeData_ShouldThrow_CustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CSV_WRONG_DATA);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenWrongDelimiter_InStateCodeData_ShouldThrow_CustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenMissingHeader_InStateCodeData_ShouldThrow_CustomException() {
		try {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_HEADER_MISSING);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}
}
