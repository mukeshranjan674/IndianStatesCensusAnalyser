package com.capgemini.sensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;

public class CSVStateCensus {

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_DATA));) {
			Iterator<StateCensusData> csvCensusIterator = new CSVBuilder().getCsvFileIterator(reader,
					StateCensusData.class);
			int numOfRecords = new CSVBuilder().getCount(csvCensusIterator);
			return numOfRecords;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}

	}
}
