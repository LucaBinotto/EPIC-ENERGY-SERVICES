package it.epicode.be.service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Comune;

public interface ComuneService {

	Comune save(Comune comune);

	Comune update(Comune newComune) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
