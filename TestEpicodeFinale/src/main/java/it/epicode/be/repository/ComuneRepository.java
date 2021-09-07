package it.epicode.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.model.Comune;

@Repository
public interface ComuneRepository  extends JpaRepository<Comune,Long>{
	
	Optional<Comune> findByNomeAndProvinciaNome(String comune, String provincia);
}
