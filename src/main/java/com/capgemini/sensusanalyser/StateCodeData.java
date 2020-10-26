package com.capgemini.sensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCodeData {

	@CsvBindByName(column = "stateName", required = true)
	public String stateName;;

	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;

	public String getStateName() {
		return stateName;
	}

	public String getStateCode() {
		return stateCode;
	}
}
