package it.epicode.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Provincia;

@Repository
public interface ProvinciaRepository  extends JpaRepository<Provincia,Long>{

	Optional<Provincia> findByNome(String provincia);

}
