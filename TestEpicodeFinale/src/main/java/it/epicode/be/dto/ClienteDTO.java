package it.epicode.be.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.epicode.be.model.Indirizzo;
import lombok.Data;

@Data
public class ClienteDTO {
	//TODO

	private Long id;
	private String ragioneSociale;
	public enum TipoSocieta {PA,SAS,SPA,SRL}
	
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
	
	
	
	private Indirizzo sedeLegale;
	
	private Indirizzo sedeOperativa;
}
