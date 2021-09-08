package it.epicode.be.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Cliente;
import it.epicode.be.repository.ClienteRepository;
import it.epicode.be.service.ClienteService;

@Service
public class ImpClienteService implements ClienteService{
	
	@Autowired
	private ClienteRepository clr;
	@Value("${exception.entitynotfound}")
	String entitynotfound;
	
	
	@Override
	public Cliente save(Cliente cliente) {
		return clr.save(cliente);
	}
	
	@Override
	public Cliente update(Cliente newCliente) throws EntityNotFoundException {
		Optional<Cliente> old = clr.findById(newCliente.getId());
		if (old.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Cliente.class);
		}
		return clr.save(newCliente);
	}
	
	@Override
	public void delete(Long id) throws EntityNotFoundException {
		try {
			clr.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, Cliente.class);
		}
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clr.findAll(pageable);
	}
}
