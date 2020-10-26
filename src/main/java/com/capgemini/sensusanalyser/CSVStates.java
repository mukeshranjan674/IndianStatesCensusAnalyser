package com.capgemini.sensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;
import com.opencsvbuilder.CSVBuilderFactory;
import com.opencsvbuilder.CSVException;
import com.opencsvbuilder.ICSVBuilder;

public class CSVStates {

	public List<StateCodeData> loadIndianStateCode(String STATE_CODE_DATA) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CODE_DATA));) {
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<StateCodeData> stateCodeList = icsvBuilder.getCsvFileList(reader, StateCodeData.class);
			return stateCodeList;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		} catch (CSVException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}
}
