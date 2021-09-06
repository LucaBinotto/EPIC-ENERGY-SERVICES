package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Component
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique=true, nullable=false)
	private Long numero;
	private Integer anno;
	private LocalDate data;
	private BigDecimal importo;
	@ManyToOne
	private StatoFattura stato;
	
	
	public Fattura() {
		data = LocalDate.now();
		anno = LocalDate.now().getYear();
	}
	
	
}
