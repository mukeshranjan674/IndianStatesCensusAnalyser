package com.capgemini.sensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;

public class CSVStates {

	public int loadIndianStateCode(String STATE_CODE_DATA) throws CensusAnalyserException {
		int numOfRecords = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CODE_DATA));) {
			Iterator<StateCodeData> csvStateCodeIterator = new CsvIterator().getCsvFileIterator(reader,
					StateCodeData.class);
			Iterable<StateCodeData> stateCodeIterable = () -> csvStateCodeIterator;
			numOfRecords = (int) StreamSupport.stream(stateCodeIterable.spliterator(), false).count();
			return numOfRecords;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}

	}
}
