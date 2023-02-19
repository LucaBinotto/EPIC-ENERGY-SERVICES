package it.epicode.be.dto;

import it.epicode.be.model.Comune;
import it.epicode.be.model.Provincia;
import lombok.Data;

@Data
public class ComuneDTO {

	private Long id;
	private String codiceComune;
	private String nome;
	private Long idProvincia;
	private String provincia;

	public static ComuneDTO fromComune(Comune c) {
		ComuneDTO cDto = new ComuneDTO();
		cDto.setId(c.getId());
		cDto.setCodiceComune(c.getCodiceComune());
		cDto.setNome(c.getNome());
		if (c.getProvincia() != null) {
			cDto.setIdProvincia(c.getProvincia().getId());
			cDto.setProvincia(c.getProvincia().getNome());
		}
		return cDto;
	}

	public Comune toComune() {
		Comune com = new Comune();
		com.setId(id);
		com.setCodiceComune(codiceComune);
		com.setNome(nome);
		if (idProvincia != null || provincia != null) {
			Provincia prov = new Provincia();
			prov.setId(idProvincia);
			prov.setNome(provincia);
			com.setProvincia(prov);
		}
		return com;
	}
}
