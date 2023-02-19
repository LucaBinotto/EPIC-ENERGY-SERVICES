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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;
	private String codiceComune;
	private String nome;
	@ManyToOne
	private Provincia provincia;
	
	
	public static Comune fromString(String[] comuStr) {
		Comune comu = new Comune();
		comu.setNome(comuStr[2]);
		comu.setCodiceComune(comuStr[1]);
		
		Provincia prov = new Provincia();
		prov.setNome(comuStr[3]);
		
		comu.setProvincia(prov);
		
		return comu;
	}
}
