package it.epicode.be.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.repository.ProvinciaRepository;
import it.epicode.be.service.ProvinciaService;

@Service
public class ImpProvinciaService implements ProvinciaService{

	@Autowired
	private ProvinciaRepository prr;
}
