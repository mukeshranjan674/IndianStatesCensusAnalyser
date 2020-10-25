package com.capgemini.sensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;
import com.csvbuilder.CSVBuilderFactory;
import com.csvbuilder.CSVException;
import com.csvbuilder.ICSVBuilder;

public class CSVStateCensus {

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_DATA));) {
			ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<StateCensusData> csvCensusIterator = icsvBuilder.getCsvFileIterator(reader, StateCensusData.class);
			int numOfRecords = this.getCount(csvCensusIterator);
			return numOfRecords;
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		} catch (CSVException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	public <E> int getCount(Iterator<E> csvIterator) {
		Iterable<E> censusIterable = () -> csvIterator;
		int numOfRecords = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();
		return numOfRecords;
	}
}
