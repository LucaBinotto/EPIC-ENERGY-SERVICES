package it.epicode.be.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente,Long>{

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

	Optional<Cliente> findByPartitaIva(String partitaIva);

}
