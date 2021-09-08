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

import it.epicode.be.dto.FatturaDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Fattura;
import it.epicode.be.service.FatturaService;

@RestController
@RequestMapping("/api/fattura")
public class FatturaController {
	
	@Autowired
	FatturaService fas;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<FatturaDTO>> listaFattura(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Fattura> fatture = fas.findAll(pageable);
		Page<FatturaDTO> fattureDto = fatture.map(FatturaDTO::fromFattura);
				
		return new ResponseEntity<>(fattureDto, HttpStatus.OK);
	}
	
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<FatturaDTO> save(@RequestBody FatturaDTO fatturaDto) {
		Fattura fattura = fatturaDto.toFattura();
		Fattura inserted = fas.save(fattura);
		return new ResponseEntity<>(FatturaDTO.fromFattura(inserted),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long numero, @RequestBody FatturaDTO fatturaDto) {
		if(!numero.equals(fatturaDto.getNumero())) {
			return new ResponseEntity<>("Il numero fattura non corrisponde",HttpStatus.BAD_REQUEST);
		}
		Fattura fattura = fatturaDto.toFattura();
		Fattura updated;
		try {
			updated = fas.update(fattura);
			return new ResponseEntity<>(FatturaDTO.fromFattura(updated),HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			fas.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
