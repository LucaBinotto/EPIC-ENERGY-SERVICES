package it.epicode.be.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.repository.FatturaRepository;
import it.epicode.be.service.FatturaService;

@Service
public class ImpFatturaService implements FatturaService {

	@Autowired
	private FatturaRepository far;
}
