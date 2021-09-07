package it.epicode.be.service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Fattura;

public interface FatturaService {

	Fattura save(Fattura fattura);

	Fattura update(Fattura newFattura) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
