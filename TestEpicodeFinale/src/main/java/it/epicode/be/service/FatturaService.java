package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Fattura;

public interface FatturaService {

	Fattura save(Fattura fattura);

	Fattura update(Fattura newFattura) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Fattura> findAll(Pageable pageable);

}
