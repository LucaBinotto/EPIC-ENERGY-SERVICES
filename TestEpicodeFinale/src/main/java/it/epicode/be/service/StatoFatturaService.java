package it.epicode.be.service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.StatoFattura;

public interface StatoFatturaService {

	StatoFattura save(StatoFattura statoFattura);

	StatoFattura update(StatoFattura newStatoFattura) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
