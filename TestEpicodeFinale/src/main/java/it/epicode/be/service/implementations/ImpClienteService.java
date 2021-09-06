package it.epicode.be.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.repository.ClienteRepository;
import it.epicode.be.service.ClienteService;

@Service
public class ImpClienteService implements ClienteService{
	
	@Autowired
	private ClienteRepository clr;
	
}
