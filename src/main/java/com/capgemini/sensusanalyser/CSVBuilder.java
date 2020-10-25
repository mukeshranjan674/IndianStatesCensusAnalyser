package com.capgemini.sensusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVBuilder {

	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			Iterator<E> csvCensusIterator = csvToBean.iterator();
			return csvCensusIterator;
		}catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), ExceptionType.UNABLE_TO_PARSE);
		} 
	}
	
	public <E> int getCount(Iterator<E> csvIterator) {
		Iterable<E> censusIterable = () -> csvIterator;
		int numOfRecords = (int) StreamSupport.stream(censusIterable.spliterator(), false).count();
		return numOfRecords;
	}
}