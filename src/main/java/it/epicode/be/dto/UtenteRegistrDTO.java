package it.epicode.be.dto;

import it.epicode.be.model.Utente;
import lombok.Data;

@Data
public class UtenteRegistrDTO {

	private Long id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private String password;
	
	public Utente toUtente() {
		Utente usr = new Utente();
		
		usr.setUsername(username);
		usr.setName(name);
		usr.setSurname(surname);
		usr.setEmail(email);
		usr.setPassword(password);
		return usr;
	}
}
