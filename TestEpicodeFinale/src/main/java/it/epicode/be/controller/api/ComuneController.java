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

import it.epicode.be.dto.ComuneDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Comune;
import it.epicode.be.service.ComuneService;

@RestController
@RequestMapping("/api/comune")
public class ComuneController {

	@Autowired
	ComuneService cos;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<ComuneDTO>> listaComune(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Comune> comuni = cos.findAll(pageable);
		Page<ComuneDTO> comuniDto = comuni.map(ComuneDTO::fromComune);
				
		return new ResponseEntity<>(comuniDto, HttpStatus.OK);
	}
	
	//TODO correzione POST E PUT
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ComuneDTO> save(@RequestBody ComuneDTO comuneDto) {
		Comune comune = comuneDto.toComune();
		Comune inserted = cos.save(comune);
		return new ResponseEntity<>(ComuneDTO.fromComune(inserted),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ComuneDTO comuneDto) {
		if(!id.equals(comuneDto.getId())) {
			return new ResponseEntity<>("L'id non corrisponde",HttpStatus.BAD_REQUEST);
		}
		Comune comune = comuneDto.toComune();
		Comune updated;
		try {
			updated = cos.update(comune);
			return new ResponseEntity<>(ComuneDTO.fromComune(updated),HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			cos.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
