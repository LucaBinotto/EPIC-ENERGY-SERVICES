package it.epicode.be.service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Utente;

public interface UtenteService{

	Utente save(Utente u);

	Utente update(Utente newUtente) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;
	
	
}
