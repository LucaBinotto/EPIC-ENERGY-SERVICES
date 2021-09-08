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

import it.epicode.be.dto.ClienteDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Cliente;
import it.epicode.be.service.ClienteService;
import it.epicode.be.service.IndirizzoService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService cls;
	@Autowired
	IndirizzoService ins;
	
	@GetMapping //Restituisce tutti i clienti paginati
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<ClienteDTO>> listaCliente(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Cliente> clienti = cls.findAll(pageable);
		Page<ClienteDTO> clientiDto = clienti.map(ClienteDTO::fromCliente);
				
		return new ResponseEntity<>(clientiDto, HttpStatus.OK);
	}
	
	
	@PostMapping //salva un cliente
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO clienteDto) {
		Cliente cliente = clienteDto.toCliente();
		Cliente inserted = cls.save(cliente);
		return new ResponseEntity<>(ClienteDTO.fromCliente(inserted),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDto) {
		if(!id.equals(clienteDto.getId())) {
			return new ResponseEntity<>("L'id non corrisponde",HttpStatus.BAD_REQUEST);
		}
		Cliente cliente = clienteDto.toCliente();
		Cliente updated;
		try {
			updated = cls.update(cliente);
			return new ResponseEntity<>(ClienteDTO.fromCliente(updated),HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			cls.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
