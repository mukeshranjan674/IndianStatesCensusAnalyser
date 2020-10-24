package com.capgemini.sensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVStateCensus {

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		int numOfRecords = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_DATA));) {
			CsvToBean<StateCensusData> csvToBean = new CsvToBeanBuilder<StateCensusData>(reader)
					.withType(StateCensusData.class).withIgnoreLeadingWhiteSpace(true).build();

			Iterator<StateCensusData> csvCensusIterator = csvToBean.iterator();
			Iterable<StateCensusData> censusIterable = () -> csvCensusIterator;
			numOfRecords = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.NO_FILE_FOUND);
		}
		return numOfRecords;
	}
}
