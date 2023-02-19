package it.epicode.be.dto;

import it.epicode.be.model.StatoFattura;
import lombok.Data;

@Data
public class StatoFatturaDTO {

	private Long id;
	private String stato;
	
	
	public static StatoFatturaDTO fromStatoFattura(StatoFattura sf) {
		StatoFatturaDTO sfDto = new StatoFatturaDTO();
		sfDto.setId(sf.getId());
		sfDto.setStato(sf.getStato());
		return sfDto;
	}
	
	public StatoFattura toStatoFattura() {
		StatoFattura stf = new StatoFattura();
		stf.setId(id);
		stf.setStato(stato);
		return stf;
	}
	
	
}
