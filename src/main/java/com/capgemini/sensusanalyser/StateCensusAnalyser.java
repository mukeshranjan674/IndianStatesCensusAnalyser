package com.capgemini.sensusanalyser;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class StateCensusAnalyser {

	public StateCensusAnalyser() {

	}

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		return new CSVStateCensus().loadIndiaCensusData(STATE_CSV_DATA).size();
	}

	public int loadIndianStateCode(String STATE_CODE_DATA) throws CensusAnalyserException {
		return new CSVStates().loadIndianStateCode(STATE_CODE_DATA).size();
	}

	/**
	 * UC3
	 * 
	 * @param STATE_CENSUS_DATA
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = new CSVStateCensus().loadIndiaCensusData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
				.sorted(Comparator.comparing(StateCensusData::getState)).collect(Collectors.toList());
		return toJson(sortedStateCensusList);
	}

	/**
	 * UC4
	 * 
	 * @param STATE_CODE_DATA
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getStateCodeWiseSortedData(String STATE_CODE_DATA) throws CensusAnalyserException {
		List<StateCodeData> stateCodeList = new CSVStates().loadIndianStateCode(STATE_CODE_DATA);
		List<StateCodeData> sortedStateList = stateCodeList.stream()
				.sorted(Comparator.comparing(StateCodeData::getStateCode)).collect(Collectors.toList());
		return toJson(sortedStateList);
	}

	/**
	 * UC5
	 * 
	 * @param STATE_CENSUS_DATA
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = new CSVStateCensus().loadIndiaCensusData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
				.sorted(Comparator.comparing(StateCensusData::getPopulation).reversed()).collect(Collectors.toList());
		return toJson(sortedStateCensusList);
	}

	/**
	 * UC6
	 * 
	 * @param STATE_CENSUS_DATA
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getPopulationDensityWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = loadIndianCensuaData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
				.sorted(Comparator.comparing(StateCensusData::getDensityPerSqKm).reversed())
				.collect(Collectors.toList());
		return toJson(sortedStateCensusList);
	}

	/**
	 * UC7
	 * 
	 * @param STATE_CENSUS_DATA
	 * @return
	 * @throws CensusAnalyserException
	 */
	public String getAreaWiseSortedCensusData(String STATE_CENSUS_DATA) throws CensusAnalyserException {
		List<StateCensusData> stateCensusList = new CSVStateCensus().loadIndiaCensusData(STATE_CENSUS_DATA);
		List<StateCensusData> sortedStateCensusList = stateCensusList.stream()
				.sorted(Comparator.comparing(StateCensusData::getAreaInSqKm).reversed()).collect(Collectors.toList());
		return toJson(sortedStateCensusList);
	}

	public List<StateCensusData> loadIndianCensuaData(String PATH) throws CensusAnalyserException {
		return new CSVStateCensus().loadIndiaCensusData(PATH);
	}

	public <E> String toJson(List<E> list) {
		return new Gson().toJson(list);
	}
}
