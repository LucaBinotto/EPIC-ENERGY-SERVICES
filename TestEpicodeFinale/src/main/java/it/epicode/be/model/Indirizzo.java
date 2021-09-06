package it.epicode.be.model;

import lombok.Data;

@Data
public class Indirizzo {

	private String via;
	private String civico;
	private String localita;
	private String cap;
	private Comune comune;
}
