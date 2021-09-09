package it.epicode.be.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Cliente;

public interface ClienteService {

	void delete(Long id) throws EntityNotFoundException;

	Cliente update(Cliente newCliente) throws EntityNotFoundException;

	Cliente save(Cliente cliente);
	
	Page<Cliente> findAll(Pageable pageable);

	Page<Cliente> findByRagioneSociale(String ragioneSociale, Pageable pageable);

	Page<Cliente> findByRagioneSocialeContaining(String ragioneSociale, Pageable pageable);

	Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal bigDecimal, BigDecimal bigDecimal2, Pageable pageable);

	Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(BigDecimal bigDecimal, Pageable pageable);

	Page<Cliente> findByFatturatoAnnualeLessThanEqual(BigDecimal bigDecimal, Pageable pageable);

	Page<Cliente> findByDataInserimentoBetween(LocalDate localDate, LocalDate localDate2, Pageable pageable);

	Page<Cliente> findByDataInserimentoGreaterThanEqual(LocalDate localDate, Pageable pageable);

	Page<Cliente> findByDataInserimentoLessThanEqual(LocalDate localDate, Pageable pageable);

	Page<Cliente> findByDataUltimoContattoBetween(LocalDate localDate, LocalDate localDate2, Pageable pageable);

	Page<Cliente> findByDataUltimoContattoGreaterThanEqual(LocalDate localDate, Pageable pageable);

	Page<Cliente> findByDataUltimoContattoLessThanEqual(LocalDate localDate, Pageable pageable);

	Cliente findById(Long idCliente) throws EntityNotFoundException;
}
