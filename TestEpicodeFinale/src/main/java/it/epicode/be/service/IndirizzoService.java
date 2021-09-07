package it.epicode.be.service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Indirizzo;

public interface IndirizzoService {

	Indirizzo save(Indirizzo indirizzo);

	Indirizzo update(Indirizzo newIndirizzo) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
