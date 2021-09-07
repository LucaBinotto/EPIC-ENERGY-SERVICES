package it.epicode.be.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupGestionale implements CommandLineRunner{

	Logger log = LoggerFactory.getLogger(StartupGestionale.class);
	
	@Override
	public void run(String... args) throws Exception {

		log.info("Running");
		//popolazioneDatabase();
	}
}
