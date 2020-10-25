package com.capgemini.sensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVStateCensus {

	public int loadIndiaCensusData(String STATE_CSV_DATA) throws CensusAnalyserException {
		int numOfRecords = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_DATA));) {
			CsvToBeanBuilder<StateCensusData> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(StateCensusData.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCensusData> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCensusData> csvCensusIterator = csvToBean.iterator();
			Iterable<StateCensusData> censusIterable = () -> csvCensusIterator;
			numOfRecords = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();
			return numOfRecords;
		} catch (IOException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.FILE_NOT_FOUND);
		}catch (IllegalStateException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.UNABLE_TO_PARSE);
		}catch (RuntimeException e) {
				throw new CensusAnalyserException(e.getMessage(), ExceptionType.WRONG_CSV_DATA);
		}
		
	}
}
