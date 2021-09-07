package it.epicode.be.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import it.epicode.be.model.Comune;
import lombok.Data;

@Data
public class IndirizzoDTO {
	//TODO
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	private String via;
	private String civico;
	private String localita;
	private String cap;
	
	private String comune;//???
}
