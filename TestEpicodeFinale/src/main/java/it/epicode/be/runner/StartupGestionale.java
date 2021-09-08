package it.epicode.be.runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Component
public class StartupGestionale implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(StartupGestionale.class);

	@Override
	public void run(String... args) throws Exception {

		log.info("Running");
		// popolazioneDatabase();
		
		//leggiCSV();
		
		
	}

	public List<String[]> leggiCSV() throws IOException{
		FileReader reader = new FileReader(new File("percorso"));
		
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(';')
				.withIgnoreQuotations(true) //inutile per questo progetto, non ci sono apici
				.build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();
		List<String[]> dati = csvReader.readAll();
		
		return dati;
	}
}
