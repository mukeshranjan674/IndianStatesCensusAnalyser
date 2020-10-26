package com.capgemini.sensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {

	@CsvBindByName(column = "State")
	private String state;

	@CsvBindByName(column = "Population", required = true)
	private int population;

	@CsvBindByName(column = "AreaInSqKm")
	private int areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm")
	private int densityPerSqKm;

	public String getState() {
		return state;
	}

	public int getPopulation() {
		return population;
	}

	public int getAreaInSqKm() {
		return areaInSqKm;
	}

	public int getDensityPerSqKm() {
		return densityPerSqKm;
	}
}
