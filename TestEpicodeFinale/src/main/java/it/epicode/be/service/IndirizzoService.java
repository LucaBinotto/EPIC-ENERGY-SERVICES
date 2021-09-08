package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Indirizzo;

public interface IndirizzoService {

	Indirizzo save(Indirizzo indirizzo);

	Indirizzo update(Indirizzo newIndirizzo) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Indirizzo> findAll(Pageable pageable);

}
