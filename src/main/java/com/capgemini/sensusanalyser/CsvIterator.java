package com.capgemini.sensusanalyser;

import java.io.Reader;
import java.util.Iterator;

import com.capgemini.sensusanalyser.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvIterator {

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
}
