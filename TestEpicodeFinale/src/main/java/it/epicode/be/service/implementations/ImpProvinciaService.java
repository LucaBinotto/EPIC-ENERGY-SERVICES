package it.epicode.be.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Provincia;
import it.epicode.be.repository.ProvinciaRepository;
import it.epicode.be.service.ProvinciaService;

@Service
public class ImpProvinciaService implements ProvinciaService {

	@Autowired
	private ProvinciaRepository prr;
	@Value("${exception.entitynotfound}")
	String entitynotfound;

	@Override
	public Provincia save(Provincia provincia) {
		return prr.save(provincia);
	}

	@Override
	public Provincia update(Provincia newProvincia) throws EntityNotFoundException {
		Optional<Provincia> old = prr.findById(newProvincia.getId());
		if (old.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Provincia.class);
		}
		return prr.save(newProvincia);
	}

	@Override
	public void delete(Long id) throws EntityNotFoundException {
		try {
			prr.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, Provincia.class);
		}
	}

	@Override
	public Page<Provincia> findAll(Pageable pageable) {
		return prr.findAll(pageable);
	}

	@Override
	public Provincia findById(Long id) throws EntityNotFoundException {
		Optional<Provincia> found = prr.findById(id);
		if (found.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Provincia.class);
		}
		return found.get();
	}

	@Override
	public Provincia findByNome(String provincia) throws EntityNotFoundException {
		Optional<Provincia> found = prr.findByNome(provincia);
		if (found.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Provincia.class);
		}		return found.get();
	}
}
