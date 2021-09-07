package it.epicode.be.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Fattura;
import it.epicode.be.model.StatoFattura;
import lombok.Data;

@Data
public class FatturaDTO {

	private Long numero;
	private Integer anno;
	private LocalDate data;
	private BigDecimal importo;
	private Long idStato;
	private String stato;
	private Long idCliente;
	private String cliente;

	public static FatturaDTO fromFattura(Fattura f) {
		FatturaDTO fDto = new FatturaDTO();
		fDto.setNumero(f.getNumero());
		fDto.setAnno(f.getAnno());
		fDto.setData(f.getData());
		fDto.setImporto(f.getImporto());
		fDto.setIdStato(f.getStato().getId());
		fDto.setStato(f.getStato().getStato());
		fDto.setIdCliente(f.getCliente().getId());
		fDto.setCliente(f.getCliente().getRagioneSociale());
		return fDto;
	}

	public Fattura toFattura() {
		Fattura fat = new Fattura();
		fat.setNumero(numero);
		fat.setAnno(anno);
		fat.setData(data);
		fat.setImporto(importo);

		StatoFattura stf = new StatoFattura();
		stf.setId(idStato);
		stf.setStato(stato);
		fat.setStato(stf);
		
		Cliente cli = new Cliente();
		cli.setId(idCliente);
		cli.setRagioneSociale(cliente);
		fat.setCliente(cli);
		// TODO pulire codice
//		if (str.findByStato(stato).isPresent()) {
//			fat.setStato(str.findByStato(stato).get());
//		}
//		if (clr.findById(idCliente).isPresent()) {
//			fat.setCliente(clr.findById(idCliente).get());
//		}
		return fat;
	}

}
