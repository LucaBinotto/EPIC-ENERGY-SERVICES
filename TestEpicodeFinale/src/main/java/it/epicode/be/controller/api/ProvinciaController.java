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

import it.epicode.be.dto.ProvinciaDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Provincia;
import it.epicode.be.service.ProvinciaService;

@RestController
@RequestMapping("/api/provincia")
public class ProvinciaController {

	@Autowired
	ProvinciaService prs;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<ProvinciaDTO>> listaProvincia(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Provincia> provincie = prs.findAll(pageable);
		Page<ProvinciaDTO> provincieDto = provincie.map(ProvinciaDTO::fromProvincia);
				
		return new ResponseEntity<>(provincieDto, HttpStatus.OK);
	}
	
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ProvinciaDTO> save(@RequestBody ProvinciaDTO provinciaDto) {
		Provincia provincia = provinciaDto.toProvincia();
		Provincia inserted = prs.save(provincia);
		return new ResponseEntity<>(ProvinciaDTO.fromProvincia(inserted),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProvinciaDTO provinciaDto) {
		if(!id.equals(provinciaDto.getId())) {
			return new ResponseEntity<>("L'Id non corrisponde",HttpStatus.BAD_REQUEST);
		}
		Provincia provincia = provinciaDto.toProvincia();
		Provincia updated;
		try {
			updated = prs.update(provincia);
			return new ResponseEntity<>(ProvinciaDTO.fromProvincia(updated),HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			prs.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
