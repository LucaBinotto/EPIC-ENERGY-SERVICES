package it.epicode.be.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.dto.UtenteDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Utente;
import it.epicode.be.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	UtenteService uts;
	//TODO da valutare quali api lasciare per gestione utenti
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<UtenteDTO>> listaUtente(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Utente> utenti = uts.findAll(pageable);
		Page<UtenteDTO> utentiDto = utenti.map(UtenteDTO::fromUtente);
				
		return new ResponseEntity<>(utentiDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long numero, @RequestBody UtenteDTO utenteDto) {
		if(!numero.equals(utenteDto.getId())) {
			return new ResponseEntity<>("L'Id non corrisponde",HttpStatus.BAD_REQUEST);
		}
		Utente utente = utenteDto.toUtente();
		Utente updated;
		try {
			updated = uts.update(utente);
			return new ResponseEntity<>(UtenteDTO.fromUtente(updated),HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			uts.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
