package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Comune;

public interface ComuneService {

	Comune save(Comune comune);

	Comune update(Comune newComune) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Comune> findAll(Pageable pageable);

	Comune findById(Long id) throws EntityNotFoundException;

}
