package it.epicode.be.dto;

import it.epicode.be.model.Provincia;
import lombok.Data;

@Data
public class ProvinciaDTO {
	
	private Long id;
	private String nome;
	private String sigla;
	private String regione;
	
	public static ProvinciaDTO fromProvincia(Provincia p) {
		ProvinciaDTO prDto = new ProvinciaDTO();
		prDto.setId(p.getId());
		prDto.setNome(p.getNome());
		prDto.setSigla(p.getSigla());
		prDto.setRegione(p.getRegione());
		return prDto;
	}
	
	public Provincia toProvincia() {
		Provincia pr = new Provincia();
		pr.setId(id);
		pr.setNome(nome);
		pr.setSigla(sigla);
		pr.setRegione(regione);
		return pr;
	}
}
