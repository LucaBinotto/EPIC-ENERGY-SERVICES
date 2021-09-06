package it.epicode.be.model;

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
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique=true, nullable=false)
	private Long id;
	private String via;
	private String civico;
	private String localita;
	private String cap;
	@ManyToOne
	private Comune comune;
}
