package com.capgemini.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.jsonutility.Json;
import com.capgemini.sensusanalyser.CensusAnalyserException;
import com.capgemini.sensusanalyser.StateCensusAnalyser;
import com.capgemini.sensusanalyser.StateCensusData;
import com.capgemini.sensusanalyser.StateCodeData;
import com.google.gson.Gson;

public class StateCensusAnalyserTest {

	private static final String STATE_CENSUS_DATA = "./src/test/resources/StateCensusData.csv";
	private static final String STATE_CODE_DATA = "./src/test/resources/StateCodeData.csv";
	private static final String CSV_WRONG_DATA = "./src/test/resources/WrongData.csv";
	private static final String WRONG_FILE_PATH = "./src/main/resources/StateCensusData.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/CensusWrongDelimiterData.csv";
	private static final String INDIAN_CENSUS_CSV_HEADER_MISSING = "./src/test/resources/CensusHeaderMissing.csv";
	private static final String STATE_CODE_CSV_WRONG_DELIMITER = "./src/test/resources/CodeWrongDelimiter.csv";
	private static final String STATE_CODE_CSV_HEADER_MISSING = "./src/test/resources/CodeHeaderMissing.csv";

	StateCensusAnalyser stateCensusAnalyser = null;

	@Before
	public void Setup() {
		stateCensusAnalyser = new StateCensusAnalyser();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CensusAnalyserException.class);
	}

	@Test
	public void givenCsvPath_ShouldReturn_NumberOfRecords() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(STATE_CENSUS_DATA);
			assertEquals(28, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenWrongFile_ShouldThrow_CustomException() {
		try {
			stateCensusAnalyser.loadIndiaCensusData(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
		}
	}

	@Test
	public void givenRightCsvFile_ButWrongType_ShouldThrow_CustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CSV_WRONG_DATA);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenWrongDelimiter_InIndiaCensusData_ShouldThrow_CustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenMissingHeader_InIndiaCensusData_ShouldThrow_CustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_HEADER_MISSING);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenCsvPath_InStateCodeData_ShouldReturn_NumberOfRecords() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_DATA);
			assertEquals(38, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenWrongCsvPath_InStateCodeData_ShouldThrow_CustomException() {
		try {
			stateCensusAnalyser.loadIndianStateCode(WRONG_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND, e.type);
		}
	}

	@Test
	public void givenRightStateCsvFile_ButWrongType_InStateCodeData_ShouldThrow_CustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndiaCensusData(CSV_WRONG_DATA);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenWrongDelimiter_InStateCodeData_ShouldThrow_CustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenMissingHeader_InStateCodeData_ShouldThrow_CustomException() {
		try {
			int numOfRecords = stateCensusAnalyser.loadIndianStateCode(STATE_CODE_CSV_HEADER_MISSING);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CensusAnalyserException {
		String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData(STATE_CENSUS_DATA);
		StateCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, StateCensusData[].class);
		assertEquals("Andhra Pradesh", censusCSV[0].getState());
		assertEquals("West Bengal", censusCSV[censusCSV.length-1].getState());
	}
	
	@Test
	public void givenStateCodeData_WhenSortedOnStateCode_ShouldReturnSortedResult() throws CensusAnalyserException {
		String sortedStateCodeData = stateCensusAnalyser.getStateCodeWiseSortedData(STATE_CODE_DATA);
		StateCodeData[] stateCodeCSV = new Gson().fromJson(sortedStateCodeData, StateCodeData[].class);
		assertEquals("AN", stateCodeCSV[0].getStateCode());
		assertEquals("WB", stateCodeCSV[stateCodeCSV.length-1].getStateCode());
	}
	
	@Test
	public void givenStateCodeData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
		String sortedCensusData = stateCensusAnalyser.getPopulationWiseSortedCensusData(STATE_CENSUS_DATA);
		new Json().writeList("population", sortedCensusData);
		String readCensuslist = new Json().readList("population.json");
		StateCensusData[] censusCSV = new Gson().fromJson(sortedCensusData, StateCensusData[].class);
		StateCensusData[] censusCSVfromFile = new Gson().fromJson(readCensuslist, StateCensusData[].class);
		assertEquals(censusCSV.length, censusCSVfromFile.length);
	}
	
}
