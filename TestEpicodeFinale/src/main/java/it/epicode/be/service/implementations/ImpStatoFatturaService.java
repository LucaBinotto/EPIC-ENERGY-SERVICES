package it.epicode.be.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.StatoFattura;
import it.epicode.be.repository.StatoFatturaRepository;
import it.epicode.be.service.StatoFatturaService;

@Service
public class ImpStatoFatturaService implements StatoFatturaService{
	
	@Autowired
	private StatoFatturaRepository str;
	@Value("${exception.entitynotfound}")
	String entitynotfound;
	
	@Override
	public StatoFattura save(StatoFattura statoFattura) {
		return str.save(statoFattura);
	}
	
	@Override
	public StatoFattura update(StatoFattura newStatoFattura) throws EntityNotFoundException {
		Optional<StatoFattura> old = str.findById(newStatoFattura.getId());
		if (old.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, StatoFattura.class);
		}
		return str.save(newStatoFattura);
	}
	
	@Override
	public void delete(Long id) throws EntityNotFoundException {
		try {
			str.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, StatoFattura.class);
		}
	}

	@Override
	public Page<StatoFattura> findAll(Pageable pageable) {
		return str.findAll(pageable);
	}
}
