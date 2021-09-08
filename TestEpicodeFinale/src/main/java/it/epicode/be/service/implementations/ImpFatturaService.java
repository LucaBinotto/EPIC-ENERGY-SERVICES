package it.epicode.be.service.implementations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Fattura;
import it.epicode.be.model.StatoFattura;
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

	@Override
	public Page<Fattura> findByClienteRagioneSocialeContaining(String string, Pageable pageable) {
		return far.findByClienteRagioneSocialeContaining(string, pageable);
	}

	@Override
	public Page<Fattura> findByImportoBetween(BigDecimal bigDecimal, BigDecimal bigDecimal2, Pageable pageable) {
		return far.findByImportoBetween(bigDecimal, bigDecimal2, pageable);
	}

	@Override
	public Page<Fattura> findByImportoGreaterThanEqual(BigDecimal bigDecimal, Pageable pageable) {
		return far.findByImportoGreaterThanEqual(bigDecimal, pageable);
	}

	@Override
	public Page<Fattura> findByImportoLessThanEqual(BigDecimal bigDecimal, Pageable pageable) {
		return far.findByImportoLessThanEqual(bigDecimal, pageable);
	}

	@Override
	public Page<Fattura> findByDataBetween(LocalDate localDate, LocalDate localDate2, Pageable pageable) {
		return far.findByDataBetween(localDate, localDate2, pageable);
	}

	@Override
	public Page<Fattura> findByDataGreaterThanEqual(LocalDate localDate, Pageable pageable) {
		return far.findByDataGreaterThanEqual(localDate, pageable);
	}

	@Override
	public Page<Fattura> findByDataLessThanEqual(LocalDate localDate, Pageable pageable) {
		return far.findByDataLessThanEqual(localDate, pageable);
	}

	@Override
	public Page<Fattura> findByAnno(Integer integer, Pageable pageable) {
		return far.findByAnno(integer, pageable);
	}

	@Override
	public Page<Fattura> findByStato(StatoFattura statoFattura, Pageable pageable) {
		return far.findByStato(statoFattura, pageable);
	}
}
