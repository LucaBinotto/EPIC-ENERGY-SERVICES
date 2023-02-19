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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.dto.IndirizzoDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Indirizzo;
import it.epicode.be.service.ComuneService;
import it.epicode.be.service.IndirizzoService;

@RestController
@RequestMapping("/api/indirizzo")
public class IndirizzoController {

	@Autowired
	IndirizzoService ins;
	@Autowired
	ComuneService cos;

	// TODO aggiungere api per vedere e eliminare indirizzi non legati a clienti

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<?> getIndirizzo(@PathVariable Long id) {
		try {
			Indirizzo indirizzo = ins.findById(id);
			IndirizzoDTO indirizzoDto = IndirizzoDTO.fromIndirizzo(indirizzo);
			return new ResponseEntity<>(indirizzoDto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<IndirizzoDTO>> listaIndirizzo(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Indirizzo> indirizzi = ins.findAll(pageable);
		Page<IndirizzoDTO> indirizziDto = indirizzi.map(IndirizzoDTO::fromIndirizzo);

		return new ResponseEntity<>(indirizziDto, HttpStatus.OK);
	}

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> save(@RequestBody IndirizzoDTO indirizzoDto) {

		if (indirizzoDto.getIdComune() != null) {
			try {
				cos.findById(indirizzoDto.getIdComune());
			} catch (EntityNotFoundException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		Indirizzo indirizzo = indirizzoDto.toIndirizzo();
		Indirizzo inserte = ins.save(indirizzo);
		try {
			inserte = ins.update(indirizzo);
			return new ResponseEntity<>(IndirizzoDTO.fromIndirizzo(inserte), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody IndirizzoDTO indirizzoDto) {
		if (!id.equals(indirizzoDto.getId())) {
			return new ResponseEntity<>("L'Id non corrisponde", HttpStatus.BAD_REQUEST);
		}
		Indirizzo indirizzo = indirizzoDto.toIndirizzo();
		Indirizzo updated;
		try {
			updated = ins.update(indirizzo);
			return new ResponseEntity<>(IndirizzoDTO.fromIndirizzo(updated), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			ins.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
