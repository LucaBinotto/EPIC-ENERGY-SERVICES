package it.epicode.be.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.repository.IndirizzoRepository;
import it.epicode.be.service.IndirizzoService;

@Service
public class ImpIndirizzoService implements IndirizzoService{

	@Autowired
	private IndirizzoRepository inr;
}
