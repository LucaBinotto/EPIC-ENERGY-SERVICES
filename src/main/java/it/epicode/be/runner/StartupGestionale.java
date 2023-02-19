package it.epicode.be.runner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Comune;
import it.epicode.be.model.Provincia;
import it.epicode.be.service.ComuneService;
import it.epicode.be.service.ProvinciaService;

@Component
public class StartupGestionale implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(StartupGestionale.class);
	
	@Value("${percorsi.provincie.italiane}")
	private String provincieItaliane;
	@Value("${percorsi.comuni.italiani}")
	private String comuniItaliani;
	@Autowired
	ProvinciaService prs;
	@Autowired
	ComuneService cos;
	
	@Override
	public void run(String... args) throws Exception {

		log.info("STARTUP GESTIONALE ENTRATO");
		
//		List<String[]> province = leggiCSV(provincieItaliane);
//		List<String[]> comuni = leggiCSV(comuniItaliani);
//		salvaSuDatabase(province,comuni);
		
	}

	void salvaSuDatabase(List<String[]> province, List<String[]> comuni) throws EntityNotFoundException{
		List<Provincia> prov = new ArrayList<>();
		for(String[] a:province) {
			prs.save(Provincia.fromString(a));
			prov.add(Provincia.fromString(a));
			
		}
		
		List<Comune> comu = new ArrayList<>();
		for(String[] a:comuni) {
			cos.save(Comune.fromString(a));
			comu.add(Comune.fromString(a));
		}
		
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
		
		return csvReader.readAll();
	}
}
