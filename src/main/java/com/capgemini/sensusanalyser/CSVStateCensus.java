package com.capgemini.sensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVStateCensus {

	private List<StateCensusData> stateCensusList = new ArrayList<StateCensusData>();

	public int loadIndiaCensusData(String STATE_CSV_DATA) {
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_DATA))) {
			CsvToBean<StateCensusData> csvToBean = new CsvToBeanBuilder<StateCensusData>(reader)
					.withType(StateCensusData.class).withIgnoreLeadingWhiteSpace(true).build();

			Iterator<StateCensusData> csvCensusIterator = csvToBean.iterator();
			while (csvCensusIterator.hasNext()) {
				StateCensusData stateCensusData = csvCensusIterator.next();
				this.stateCensusList.add(stateCensusData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stateCensusList.size();
	}
}
