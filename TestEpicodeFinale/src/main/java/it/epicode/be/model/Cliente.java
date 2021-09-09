package it.epicode.be.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import it.epicode.be.security.encription.StringAttributeConverter;
import lombok.Data;

@Data
@Entity
@Component
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	private String ragioneSociale;
	public enum TipoSocieta {PA,SAS,SPA,SRL}
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoSocieta tipoSocieta;
	private String partitaIva;
	private String email;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private BigDecimal fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	@Convert(converter = StringAttributeConverter.class)
	private String iban;
	@OneToOne
	private Indirizzo sedeLegale;
	@OneToOne
	private Indirizzo sedeOperativa;
	@OneToMany(mappedBy = "cliente")
	private Set<Fattura> fatture = new HashSet<>();
}    
