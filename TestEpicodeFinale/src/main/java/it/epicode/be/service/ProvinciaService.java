package it.epicode.be.service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Provincia;

public interface ProvinciaService {

	Provincia save(Provincia provincia);

	Provincia update(Provincia newProvincia) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

}
