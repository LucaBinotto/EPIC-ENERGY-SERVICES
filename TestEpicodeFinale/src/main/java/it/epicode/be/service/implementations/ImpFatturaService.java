package it.epicode.be.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Fattura;
import it.epicode.be.repository.FatturaRepository;
import it.epicode.be.service.FatturaService;

@Service
public class ImpFatturaService implements FatturaService {

	@Autowired
	private FatturaRepository far;
	@Value("${exception.entitynotfound}")
	String entitynotfound;
	
	
	@Override
	public Fattura save(Fattura fattura) {
		return far.save(fattura);
	}
	
	@Override
	public Fattura update(Fattura newFattura) throws EntityNotFoundException {
		Optional<Fattura> old = far.findById(newFattura.getNumero());
		if (old.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Fattura.class);
		}
		return far.save(newFattura);
	}
	
	@Override
	public void delete(Long id) throws EntityNotFoundException {
		try {
			far.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, Fattura.class);
		}
	}
	
	@Override
	public Page<Fattura> findAll(Pageable pageable) {
		return far.findAll(pageable);
	}
}
