package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Provincia;

public interface ProvinciaService {

	Provincia save(Provincia provincia);

	Provincia update(Provincia newProvincia) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Provincia> findAll(Pageable pageable);

	Provincia findById(Long id) throws EntityNotFoundException;

	Provincia findByNome(String provincia) throws EntityNotFoundException;

}
