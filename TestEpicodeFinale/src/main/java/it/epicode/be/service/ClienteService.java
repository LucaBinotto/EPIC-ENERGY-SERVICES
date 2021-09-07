package it.epicode.be.service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Cliente;

public interface ClienteService {

	void delete(Long id) throws EntityNotFoundException;

	Cliente update(Cliente newCliente) throws EntityNotFoundException;

	Cliente save(Cliente cliente);

}
