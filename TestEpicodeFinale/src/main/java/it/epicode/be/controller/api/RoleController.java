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

import it.epicode.be.dto.RoleDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Role;
import it.epicode.be.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {


	@Autowired
	RoleService ros;
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<?> getRole(@PathVariable Long id) {
		try {
			Role role = ros.findById(id);
			RoleDTO roleDto = RoleDTO.fromRole(role);
			return new ResponseEntity<>(roleDto, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<RoleDTO>> listaRole(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Role> ruoli = ros.findAll(pageable);
		Page<RoleDTO> ruoliDto = ruoli.map(RoleDTO::fromRole);
				
		return new ResponseEntity<>(ruoliDto, HttpStatus.OK);
	}
	
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<RoleDTO> save(@RequestBody RoleDTO roleDto) {
		Role role = roleDto.toRole();
		Role inserted = ros.save(role);
		return new ResponseEntity<>(RoleDTO.fromRole(inserted),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody RoleDTO roleDto) {
		if(!id.equals(roleDto.getId())) {
			return new ResponseEntity<>("L'id non corrisponde",HttpStatus.BAD_REQUEST);
		}
		Role role = roleDto.toRole();
		Role updated;
		try {
			updated = ros.update(role);
			return new ResponseEntity<>(RoleDTO.fromRole(updated),HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			ros.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
