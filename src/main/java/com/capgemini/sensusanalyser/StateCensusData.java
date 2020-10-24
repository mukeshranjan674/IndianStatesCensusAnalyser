package com.capgemini.sensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCensusData {

	@CsvBindByName(column = "State")
	private String state;

	@CsvBindByName(column = "Population")
	private int population;

	@CsvBindByName(column = "AreaInSqKm")
	private int areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm")
	private int densityPerSqKm;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getDensityPerSqKm() {
		return densityPerSqKm;
	}

	public void setDensityPerSqKm(int densityPerSqKm) {
		this.densityPerSqKm = densityPerSqKm;
	}

	public int getAreaInSqKm() {
		return areaInSqKm;
	}

	public void setAreaInSqKm(int areaInSqKm) {
		this.areaInSqKm = areaInSqKm;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

}
