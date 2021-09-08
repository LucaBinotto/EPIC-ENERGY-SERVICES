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
public class Comune {

	//TODO Aggiungere Codice Comune
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	private String nome;
	@ManyToOne
	private Provincia provincia;
}
