package it.epicode.be.runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@Component
public class StartupGestionale implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(StartupGestionale.class);
	
	@Value("${percorsi.provincieItaliane}")
	private String provincieItaliane;
	@Value("${percorsi.comuniItaliani}")
	private String comuniItaliani;
	
	
	@Override
	public void run(String... args) throws Exception {

		log.info("STARTUP GESTIONALE ENTRATO");
		// popolazioneDatabase();
		
		List<String[]> stringone = leggiCSV(provincieItaliane);
		
		//String primaLetta = stringone.get(0)[0];
//		System.out.println(stringone.get(0)[0]);
//		System.out.println(stringone.get(0)[1]);
//		System.out.println(stringone.get(0)[2]);
		
		
		
		
	}

	public List<String[]> leggiCSV(String percorso) throws IOException, CsvException{
		FileReader reader = new FileReader(new File("data/province-italiane.csv"));
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(';')
				.withIgnoreQuotations(true) //inutile per questo progetto, non ci sono apici
				.build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();
		//List<String[]> dati = csvReader.readAll();
		
		return csvReader.readAll();
	}
}
