package it.epicode.be.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Role;

public interface RoleService {

	Role save(Role role);

	Role update(Role newRole) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Role> findAll(Pageable pageable);

}