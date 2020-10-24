package com.capgemini.sensusanalyser;

public class StateCensusAnalyser {

	public StateCensusAnalyser() {

	}

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		return new CSVStateCensus().loadIndiaCensusData(STATE_CSV_DATA);
	}
}
