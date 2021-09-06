package it.epicode.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long> {

	Optional<Utente> findByUsername(String username);
}
