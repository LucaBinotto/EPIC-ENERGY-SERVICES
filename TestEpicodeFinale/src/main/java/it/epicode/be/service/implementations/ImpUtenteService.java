package it.epicode.be.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.model.Utente;
import it.epicode.be.repository.UtenteRepository;
import it.epicode.be.service.UtenteService;

@Service
public class ImpUtenteService implements UtenteService{
	@Autowired
	UtenteRepository utr;
	
	@Override
	public void save(Utente u) {
		utr.save(u);
	}
}
