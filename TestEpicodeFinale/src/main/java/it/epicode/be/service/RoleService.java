package it.epicode.be.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Role;
import it.epicode.be.model.Role.RoleType;

public interface RoleService {

	Role save(Role role);

	Role update(Role newRole) throws EntityNotFoundException;

	void delete(Long id) throws EntityNotFoundException;

	Page<Role> findAll(Pageable pageable);

	Optional<Role> findByRoleType(RoleType roleUser);

	Role findById(Long id) throws EntityNotFoundException;

}