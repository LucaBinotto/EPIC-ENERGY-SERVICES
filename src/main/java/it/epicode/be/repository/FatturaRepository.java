package it.epicode.be.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Fattura;
import it.epicode.be.model.StatoFattura;

@Repository
public interface FatturaRepository  extends JpaRepository<Fattura,Long>{

	Page<Fattura> findByStato(StatoFattura statoFattura, Pageable pageable);

	Page<Fattura> findByAnno(Integer integer, Pageable pageable);

	Page<Fattura> findByDataLessThanEqual(LocalDate localDate, Pageable pageable);

	Page<Fattura> findByDataGreaterThanEqual(LocalDate localDate, Pageable pageable);

	Page<Fattura> findByDataBetween(LocalDate localDate, LocalDate localDate2, Pageable pageable);

	Page<Fattura> findByImportoLessThanEqual(BigDecimal bigDecimal, Pageable pageable);

	Page<Fattura> findByImportoGreaterThanEqual(BigDecimal bigDecimal, Pageable pageable);

	Page<Fattura> findByImportoBetween(BigDecimal bigDecimal, BigDecimal bigDecimal2, Pageable pageable);

	Page<Fattura> findByClienteRagioneSocialeContaining(String string, Pageable pageable);

}
