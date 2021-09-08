package it.epicode.be.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Role;
import it.epicode.be.repository.RoleRepository;
import it.epicode.be.service.RoleService;

@Service
public class ImpRoleService implements RoleService {

	@Autowired
	private RoleRepository ror;
	@Value("${exception.entitynotfound}")
	String entitynotfound;
	
	
	@Override
	public Role save(Role role) {
		return ror.save(role);
	}
	
	
	@Override
	public Role update(Role newRole) throws EntityNotFoundException {
		Optional<Role> old = ror.findById(newRole.getId());
		if (old.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Role.class);
		}
		return ror.save(newRole);
	}
	
	
	@Override
	public void delete(Long id) throws EntityNotFoundException {
		try {
			ror.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, Role.class);
		}
	}

	
	@Override
	public Page<Role> findAll(Pageable pageable) {
		return ror.findAll(pageable);
	}
}
