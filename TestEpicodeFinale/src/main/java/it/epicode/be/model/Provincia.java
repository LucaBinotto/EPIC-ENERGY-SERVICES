package it.epicode.be.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Component
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	@Column(unique = true)
	private String nome;
	@Column(unique = true)
	private String sigla;
	private String regione;
	
	
	public Provincia fromString(String[] provStr) {
		Provincia prov = new Provincia();
		prov.setNome(provStr[1]);
		prov.setRegione(provStr[2]);
		prov.setSigla(provStr[0]);
		return prov;
	}
	
}
