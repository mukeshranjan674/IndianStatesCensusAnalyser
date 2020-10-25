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
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CODE_DATA));) {
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<StateCensusData> csvStateCodeIterator = icsvBuilder.getCsvFileIterator(reader,
					StateCensusData.class);
			int numOfRecords = this.getCount(csvStateCodeIterator);
			return numOfRecords;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		} catch (CSVException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	public <E> int getCount(Iterator<E> csvIterator) {
		Iterable<E> censusIterable = () -> csvIterator;
		int numOfRecords = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();
		return numOfRecords;
	}
}
