package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Cliente;

public interface ClienteService {

	void delete(Long id) throws EntityNotFoundException;

	Cliente update(Cliente newCliente) throws EntityNotFoundException;

	Cliente save(Cliente cliente);
	
	Page<Cliente> findAll(Pageable pageable);
}
