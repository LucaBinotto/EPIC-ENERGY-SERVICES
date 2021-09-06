package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.ManyToOne;

import lombok.Data;

@Data
public class Fattura {

	private Long numero;
	private Integer anno;
	private LocalDate data;
	private BigDecimal importo;
	@ManyToOne
	private StatoFattura stato;
}
