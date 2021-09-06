package it.epicode.be.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.repository.ComuneRepository;
import it.epicode.be.service.ComuneService;

@Service
public class ImpComuneService implements ComuneService{
	
	@Autowired
	private ComuneRepository cor;
}
