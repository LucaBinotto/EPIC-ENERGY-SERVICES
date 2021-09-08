package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Utente;

public interface UtenteService{

	Utente save(Utente u);

	Utente update(Utente newUtente) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Utente> findAll(Pageable pageable);
	
	
}
