package it.epicode.be.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import it.epicode.be.model.Cliente;
import it.epicode.be.model.Cliente.TipoSocieta;
import it.epicode.be.model.Indirizzo;
import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;
	private String ragioneSociale;
	private String tipoSocieta;
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
	private String iban;
	private IndirizzoDTO sedeLegale;
	private IndirizzoDTO sedeOperativa;

	
	
	public static ClienteDTO fromCliente(Cliente c) {
		ClienteDTO cDto = new ClienteDTO();
		cDto.setId(c.getId());
		cDto.setRagioneSociale(c.getRagioneSociale());
		cDto.setTipoSocieta(c.getTipoSocieta().toString());
		cDto.setPartitaIva(c.getPartitaIva());
		cDto.setEmail(c.getEmail());
		cDto.setDataInserimento(c.getDataInserimento());
		cDto.setDataUltimoContatto(c.getDataUltimoContatto());
		cDto.setFatturatoAnnuale(c.getFatturatoAnnuale());
		cDto.setPec(c.getPec());
		cDto.setTelefono(c.getTelefono());
		cDto.setEmailContatto(c.getEmailContatto());
		cDto.setNomeContatto(c.getNomeContatto());
		cDto.setCognomeContatto(c.getCognomeContatto());
		cDto.setTelefonoContatto(c.getTelefonoContatto());
		cDto.setIban(c.getIban());
		if (c.getSedeLegale() != null) {
			IndirizzoDTO iDtoLeg = IndirizzoDTO.fromIndirizzo(c.getSedeLegale());
			cDto.setSedeLegale(iDtoLeg);
		}
		if (c.getSedeOperativa() != null) {
			IndirizzoDTO iDtoOpe = IndirizzoDTO.fromIndirizzo(c.getSedeOperativa());
			cDto.setSedeOperativa(iDtoOpe);
		}

		return cDto;
	}

	public Cliente toCliente(){
		Cliente cli = new Cliente();
		cli.setId(id);
		cli.setRagioneSociale(ragioneSociale);
		cli.setTipoSocieta(TipoSocieta.valueOf(tipoSocieta));
		cli.setPartitaIva(partitaIva);
		cli.setEmail(email);
		cli.setDataInserimento(dataInserimento);
		cli.setDataUltimoContatto(dataUltimoContatto);
		cli.setFatturatoAnnuale(fatturatoAnnuale);
		cli.setPec(pec);
		cli.setTelefono(telefono);
		cli.setEmailContatto(emailContatto);
		cli.setNomeContatto(nomeContatto);
		cli.setCognomeContatto(cognomeContatto);
		cli.setTelefonoContatto(telefonoContatto);
		cli.setIban(iban);
		if (sedeLegale != null) {
			Indirizzo ind = sedeLegale.toIndirizzo();
			cli.setSedeLegale(ind);
		}
		if (sedeOperativa != null) {
			Indirizzo  ind = sedeOperativa.toIndirizzo();
			cli.setSedeOperativa(ind);
		}

		return cli;
	}
}
