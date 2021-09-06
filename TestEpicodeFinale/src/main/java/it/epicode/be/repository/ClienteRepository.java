package it.epicode.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Cliente;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente,Long>{

}
