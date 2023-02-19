package it.epicode.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Indirizzo;

@Repository
public interface IndirizzoRepository  extends JpaRepository<Indirizzo,Long>{

}
