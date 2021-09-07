package it.epicode.be.dto;

import it.epicode.be.model.Utente;
import lombok.Data;

@Data
public class UtenteDTO {
	
	private Long id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private Boolean active = true;
	
	public static UtenteDTO fromUtente(Utente usr) {
		UtenteDTO usrDto = new UtenteDTO();
		usrDto.setId(usr.getId());
		usrDto.setUsername(usr.getUsername());
		usrDto.setName(usr.getName());
		usrDto.setSurname(usr.getSurname());
		usrDto.setEmail(usr.getEmail());
		return usrDto;
	}
	public Utente toUtente() {
		Utente usr = new Utente();
		usr.setId(id);
		usr.setUsername(username);
		usr.setName(name);
		usr.setSurname(surname);
		usr.setEmail(email);
		return usr;
	}
}
