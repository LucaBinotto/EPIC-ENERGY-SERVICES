package it.epicode.be.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Fattura;
import it.epicode.be.model.StatoFattura;

public interface FatturaService {

	Fattura save(Fattura fattura);

	Fattura update(Fattura newFattura) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Fattura> findAll(Pageable pageable);

	Page<Fattura> findByClienteRagioneSocialeContaining(String string, Pageable pageable);

	Page<Fattura> findByImportoBetween(BigDecimal bigDecimal, BigDecimal bigDecimal2, Pageable pageable);

	Page<Fattura> findByImportoGreaterThanEqual(BigDecimal bigDecimal, Pageable pageable);

	Page<Fattura> findByImportoLessThanEqual(BigDecimal bigDecimal, Pageable pageable);

	Page<Fattura> findByDataBetween(LocalDate localDate, LocalDate localDate2, Pageable pageable);

	Page<Fattura> findByDataGreaterThanEqual(LocalDate localDate, Pageable pageable);

	Page<Fattura> findByDataLessThanEqual(LocalDate localDate, Pageable pageable);

	Page<Fattura> findByAnno(Integer integer, Pageable pageable);

	Page<Fattura> findByStato(StatoFattura statoFattura, Pageable pageable);

	Fattura findByNumero(Long numero) throws EntityNotFoundException;

}
