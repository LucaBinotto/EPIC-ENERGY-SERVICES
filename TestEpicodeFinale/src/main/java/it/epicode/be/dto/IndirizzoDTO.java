package it.epicode.be.dto;

import it.epicode.be.model.Comune;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.model.Provincia;
import lombok.Data;

@Data
public class IndirizzoDTO {
	
	private Long id;
	private String via;
	private String civico;
	private String localita;
	private String cap;
	private Long idComune;
	private String comune;
	private Long idProvincia;
	private String provincia;

	public static IndirizzoDTO fromIndirizzo(Indirizzo i) {
		IndirizzoDTO iDto = new IndirizzoDTO();
		iDto.setId(i.getId());
		iDto.setVia(i.getVia());
		iDto.setCivico(i.getCivico());
		iDto.setLocalita(i.getLocalita());
		iDto.setCap(i.getCap());

		iDto.setIdComune(i.getComune().getId());
		iDto.setComune(i.getComune().getNome());
		iDto.setIdProvincia(i.getComune().getProvincia().getId());
		iDto.setProvincia(i.getComune().getProvincia().getNome());
		return iDto;
	}

	public Indirizzo toIndirizzo() {
		Indirizzo ind = new Indirizzo();
		ind.setId(id);
		ind.setVia(via);
		ind.setCivico(civico);
		ind.setLocalita(localita);
		ind.setCap(cap);

		Provincia prov = new Provincia();
		prov.setId(idProvincia);
		prov.setNome(provincia);
		Comune com = new Comune();
		com.setId(idComune);
		com.setNome(comune);
		com.setProvincia(prov);
		
		ind.setComune(com);
		
		return ind;
	}
}
