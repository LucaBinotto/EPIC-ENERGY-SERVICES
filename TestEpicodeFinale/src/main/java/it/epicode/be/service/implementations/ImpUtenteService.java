package it.epicode.be.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Utente;
import it.epicode.be.repository.UtenteRepository;
import it.epicode.be.service.UtenteService;

@Service
public class ImpUtenteService implements UtenteService{
	@Autowired
	UtenteRepository utr;
	@Value("${exception.entitynotfound}")
	String entitynotfound;
	
	
	@Override
	public Utente save(Utente cliente) {
		return utr.save(cliente);
	}
	
	@Override
	public Utente update(Utente newUtente) throws EntityNotFoundException {
		Optional<Utente> old = utr.findById(newUtente.getId());
		if (old.isEmpty()) {
			throw new EntityNotFoundException(entitynotfound, Utente.class);
		}
		return utr.save(newUtente);
	}
	
	@Override
	public void delete(Long id) throws EntityNotFoundException {
		try {
			utr.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(entitynotfound, Utente.class);
		}
	}
}
