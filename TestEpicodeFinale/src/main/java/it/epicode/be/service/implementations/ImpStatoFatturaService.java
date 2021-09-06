package it.epicode.be.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.repository.StatoFatturaRepository;
import it.epicode.be.service.StatoFatturaService;

@Service
public class ImpStatoFatturaService implements StatoFatturaService{
	
	@Autowired
	private StatoFatturaRepository str;
}
