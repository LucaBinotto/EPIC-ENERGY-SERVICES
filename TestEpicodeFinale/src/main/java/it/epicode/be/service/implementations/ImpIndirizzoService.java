package it.epicode.be.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.repository.IndirizzoRepository;
import it.epicode.be.service.IndirizzoService;

@Service
public class ImpIndirizzoService implements IndirizzoService{

	@Autowired
	private IndirizzoRepository inr;
	@Value("${exception.entitynotfound}")
	String entitynotfound;
	
	
	@Override
	public Indirizzo save(Indirizzo indirizzo) {
		return inr.save(indirizzo);
	}
	
	@Override
	public Indirizzo update(Indirizzo newIndirizzo) throws EntityNotFoundException {
		Optional<Indirizzo> old = inr.findById(newIndirizzo.getId());
		if (old.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Indirizzo.class);
		}
		return inr.save(newIndirizzo);
	}
	
	@Override
	public void delete(Long id) throws EntityNotFoundException {
		try {
			inr.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, Indirizzo.class);
		}
	}

	@Override
	public Page<Indirizzo> findAll(Pageable pageable) {
		return inr.findAll(pageable);
	}
}
