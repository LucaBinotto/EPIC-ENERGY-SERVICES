package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.StatoFattura;

public interface StatoFatturaService {

	StatoFattura save(StatoFattura statoFattura);

	StatoFattura update(StatoFattura newStatoFattura) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<StatoFattura> findAll(Pageable pageable);

}
