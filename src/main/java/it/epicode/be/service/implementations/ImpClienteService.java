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
import it.epicode.be.model.Cliente;
import it.epicode.be.repository.ClienteRepository;
import it.epicode.be.service.ClienteService;

@Service
public class ImpClienteService implements ClienteService {

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
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, Cliente.class);
		}
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		return clr.findAll(pageable);
	}

	@Override
	public Page<Cliente> findByRagioneSociale(String ragioneSociale, Pageable pageable) {
		return clr.findByRagioneSociale(ragioneSociale, pageable);
	}

	@Override
	public Page<Cliente> findByRagioneSocialeContaining(String ragioneSociale, Pageable pageable) {
		return clr.findByRagioneSocialeContaining(ragioneSociale, pageable);
	}

	@Override
	public Page<Cliente> findByFatturatoAnnualeBetween(BigDecimal bigDecimal, BigDecimal bigDecimal2,
			Pageable pageable) {
		return clr.findByFatturatoAnnualeBetween(bigDecimal, bigDecimal2, pageable);
	}

	@Override
	public Page<Cliente> findByFatturatoAnnualeGreaterThanEqual(BigDecimal bigDecimal, Pageable pageable) {
		return clr.findByFatturatoAnnualeGreaterThanEqual(bigDecimal, pageable);
	}

	@Override
	public Page<Cliente> findByFatturatoAnnualeLessThanEqual(BigDecimal bigDecimal, Pageable pageable) {
		return clr.findByFatturatoAnnualeLessThanEqual(bigDecimal, pageable);
	}

	@Override
	public Page<Cliente> findByDataInserimentoBetween(LocalDate localDate, LocalDate localDate2, Pageable pageable) {
		return clr.findByDataInserimentoBetween(localDate, localDate2, pageable);
	}

	@Override
	public Page<Cliente> findByDataInserimentoGreaterThanEqual(LocalDate localDate, Pageable pageable) {
		return clr.findByDataInserimentoGreaterThanEqual(localDate, pageable);
	}

	@Override
	public Page<Cliente> findByDataInserimentoLessThanEqual(LocalDate localDate, Pageable pageable) {
		return clr.findByDataInserimentoLessThanEqual(localDate, pageable);
	}

	@Override
	public Page<Cliente> findByDataUltimoContattoBetween(LocalDate localDate, LocalDate localDate2, Pageable pageable) {
		return clr.findByDataUltimoContattoBetween(localDate, localDate2, pageable);
	}

	@Override
	public Page<Cliente> findByDataUltimoContattoGreaterThanEqual(LocalDate localDate, Pageable pageable) {
		return clr.findByDataUltimoContattoGreaterThanEqual(localDate, pageable);
	}

	@Override
	public Page<Cliente> findByDataUltimoContattoLessThanEqual(LocalDate localDate, Pageable pageable) {
		return clr.findByDataUltimoContattoLessThanEqual(localDate, pageable);
	}

	@Override
	public Cliente findById(Long idCliente) throws EntityNotFoundException {
		Optional<Cliente> cliente = clr.findById(idCliente);
		if (cliente.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Cliente.class);
		}
		return cliente.get();
	}

	@Override
	public Cliente findByPartitaIva(String partitaIva) throws EntityNotFoundException {
		Optional<Cliente> cliente = clr.findByPartitaIva(partitaIva);
		if (cliente.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Cliente.class);
		}
		return cliente.get();
	}
}
