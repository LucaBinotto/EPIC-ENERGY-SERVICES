package it.epicode.be.runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

import it.epicode.be.model.Comune;
import it.epicode.be.model.Provincia;

@Component
public class StartupGestionale implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(StartupGestionale.class);
	
	@Value("${percorsi.provincie.italiane}")
	private String provincieItaliane;
	@Value("${percorsi.comuni.italiani}")
	private String comuniItaliani;
	
	
	@Override
	public void run(String... args) throws Exception {

		log.info("STARTUP GESTIONALE ENTRATO");
		
		List<String[]> province = leggiCSV(provincieItaliane);
		List<String[]> comuni = leggiCSV(comuniItaliani);
		
		List<Provincia> prov = new ArrayList<>();
		for(String[] a:province) {
			prov.add(Provincia.fromString(a));
		}
		
		List<Comune> comu = new ArrayList<>();
		for(String[] a:comuni) {
			comu.add(Comune.fromString(a));
		}
		
		System.out.println(prov.get(0));
		System.out.println(prov.get(100));
		System.out.println(comu.get(100));
			
//		System.out.println(province.get(0)[0]);
//		System.out.println(province.get(0)[1]);
//		System.out.println(province.get(0)[2]);
//		
//		System.out.println(comuni.get(0)[1]);
//		System.out.println(comuni.get(0)[2]);
//		System.out.println(comuni.get(0)[3]);
		
		
		
	}

	public List<String[]> leggiCSV(String percorso) throws IOException, CsvException{
		FileReader reader = new FileReader(new File(percorso));
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
