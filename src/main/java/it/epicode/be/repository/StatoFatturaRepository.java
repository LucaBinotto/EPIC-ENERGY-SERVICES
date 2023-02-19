package it.epicode.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.StatoFattura;

@Repository
public interface StatoFatturaRepository  extends JpaRepository<StatoFattura,Long>{
	
	Optional<StatoFattura> findByStato(String stato);
}
