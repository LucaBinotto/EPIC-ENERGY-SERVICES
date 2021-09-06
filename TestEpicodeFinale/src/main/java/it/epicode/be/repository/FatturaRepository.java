package it.epicode.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Fattura;

@Repository
public interface FatturaRepository  extends JpaRepository<Fattura,Long>{

}
