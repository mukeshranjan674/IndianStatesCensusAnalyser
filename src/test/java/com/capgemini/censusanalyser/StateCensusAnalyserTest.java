package com.capgemini.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.sensusanalyser.StateCensusAnalyser;

public class StateCensusAnalyserTest {

	private static final String STATE_CSV_DATA = "C:\\Users\\LENOVO\\eclipse-workspace"
			+ "\\IndianStateCensusAnalyser\\src\\main\\resources\\StateCensusData.csv";

	@Test
	public void whenGivenCsvPathShouldReturnNumberOfRecords() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int records = stateCensusAnalyser.loadIndiaCensusData(STATE_CSV_DATA);
		assertEquals(28, records);
	}
}
