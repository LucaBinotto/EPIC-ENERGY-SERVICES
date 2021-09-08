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

import it.epicode.be.dto.StatoFatturaDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.StatoFattura;
import it.epicode.be.service.StatoFatturaService;

@RestController
@RequestMapping("/api/statofattura")
public class StatoFatturaController {

	@Autowired
	StatoFatturaService sts;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<StatoFatturaDTO>> listaStatoFattura(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<StatoFattura> statiFattura = sts.findAll(pageable);
		Page<StatoFatturaDTO> statiFatturaDto = statiFattura.map(StatoFatturaDTO::fromStatoFattura);
				
		return new ResponseEntity<>(statiFatturaDto, HttpStatus.OK);
	}
	
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<StatoFatturaDTO> save(@RequestBody StatoFatturaDTO statoFatturaDto) {
		StatoFattura statoFattura = statoFatturaDto.toStatoFattura();
		StatoFattura inserted = sts.save(statoFattura);
		return new ResponseEntity<>(StatoFatturaDTO.fromStatoFattura(inserted),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StatoFatturaDTO statoFatturaDto) {
		if(!id.equals(statoFatturaDto.getId())) {
			return new ResponseEntity<>("L'Id non corrisponde",HttpStatus.BAD_REQUEST);
		}
		StatoFattura statoFattura = statoFatturaDto.toStatoFattura();
		StatoFattura updated;
		try {
			updated = sts.update(statoFattura);
			return new ResponseEntity<>(StatoFatturaDTO.fromStatoFattura(updated),HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			sts.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
