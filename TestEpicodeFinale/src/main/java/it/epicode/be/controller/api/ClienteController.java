package it.epicode.be.controller.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.dto.ClienteDTO;
import it.epicode.be.exception.EntityNotFoundException;
import it.epicode.be.model.Cliente;
import it.epicode.be.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	ClienteService cls;

	@GetMapping // Restituisce tutti i clienti paginati
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<ClienteDTO>> listaCliente(@RequestParam int pageNum, @RequestParam int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Cliente> clienti = cls.findAll(pageable);
		Page<ClienteDTO> clientiDto = clienti.map(ClienteDTO::fromCliente);

		return new ResponseEntity<>(clientiDto, HttpStatus.OK);
	}

	@PostMapping // salva un cliente
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ClienteDTO> save(@RequestBody ClienteDTO clienteDto) {
		Cliente cliente = clienteDto.toCliente();
		Cliente inserted = cls.save(cliente);
		return new ResponseEntity<>(ClienteDTO.fromCliente(inserted), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDto) {
		if (!id.equals(clienteDto.getId())) {
			return new ResponseEntity<>("L'id non corrisponde", HttpStatus.BAD_REQUEST);
		}
		Cliente cliente = clienteDto.toCliente();
		Cliente updated;
		try {
			updated = cls.update(cliente);
			return new ResponseEntity<>(ClienteDTO.fromCliente(updated), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			cls.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/ordina")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<ClienteDTO>> listaClienteOrdinata(@RequestParam int pageNum, @RequestParam int pageSize,
			@RequestParam boolean ragioneSociale, @RequestParam boolean fatturatoAnnuale,
			@RequestParam boolean dataInserimento, @RequestParam boolean dataUltimoContatto,
			@RequestParam boolean sedeLegale, @RequestParam boolean discendente) {
		Pageable pageable;
//		List<String> attributi;            Per Ordinare con piu paramentri, accodandoli
//		Sort b = Sort.unsorted();
//		for(String s:attributi) {
//			b.and(Sort.by(s));
//		}
//		PageRequest pageable =  PageRequest.of(pageNum, pageSize, b);
		if (discendente) {
			if (ragioneSociale) {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("ragioneSociale").descending().and(Sort.by("id")));
			} else if (fatturatoAnnuale) {
				pageable = PageRequest.of(pageNum, pageSize,
						Sort.by("fatturatoAnnuale").descending().and(Sort.by("id")));
			} else if (dataInserimento) {
				pageable = PageRequest.of(pageNum, pageSize,
						Sort.by("dataInserimento").descending().and(Sort.by("id")));
			} else if (dataUltimoContatto) {
				pageable = PageRequest.of(pageNum, pageSize,
						Sort.by("dataUltimoContatto").descending().and(Sort.by("id")));
			} else if (sedeLegale) {
				pageable = PageRequest.of(pageNum, pageSize,
						Sort.by("sedeLegale.comune.provincia").descending().and(Sort.by("id")));
			} else {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
			}
		} else {
			if (ragioneSociale) {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("ragioneSociale").and(Sort.by("id")));
			} else if (fatturatoAnnuale) {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("fatturatoAnnuale").and(Sort.by("id")));
			} else if (dataInserimento) {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("dataInserimento").and(Sort.by("id")));
			} else if (dataUltimoContatto) {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("dataUltimoContatto").and(Sort.by("id")));
			} else if (sedeLegale) {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("sedeLegale.comune.provincia").and(Sort.by("id")));
			} else {
				pageable = PageRequest.of(pageNum, pageSize, Sort.by("id"));
			}
		}
		return pager(pageable);
	}

	private ResponseEntity<Page<ClienteDTO>> pager(Pageable pageable) {
		Page<Cliente> clienti = cls.findAll(pageable);
		Page<ClienteDTO> clientiDto = clienti.map(ClienteDTO::fromCliente);
		return new ResponseEntity<>(clientiDto, HttpStatus.OK);
	}

	@GetMapping("/filtra")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<ClienteDTO>> listaClienteFiltrata(@RequestParam int pageNum, @RequestParam int pageSize,
			@RequestParam Optional<String> ragioneSociale, 
			@RequestParam Optional<BigDecimal> fatturatoAnnualeMinimo,
			@RequestParam Optional<BigDecimal> fatturatoAnnualeMassimo,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dataInserimentoMinima,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dataInserimentoMassima,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dataUltimoContattoMinima,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> dataUltimoContattoMassima) {
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("id"));
		Page<Cliente> clienti = null;
		if (ragioneSociale.isPresent()) {
			clienti = cls.findByRagioneSocialeContaining(ragioneSociale.get(), pageable);
		} else if (fatturatoAnnualeMinimo.isPresent() && fatturatoAnnualeMassimo.isPresent()) {
			clienti = cls.findByFatturatoAnnualeBetween(fatturatoAnnualeMinimo.get(), fatturatoAnnualeMassimo.get(),
					pageable);
		} else if (fatturatoAnnualeMinimo.isPresent()) {
			clienti = cls.findByFatturatoAnnualeGreaterThanEqual(fatturatoAnnualeMinimo.get(), pageable);
		} else if (fatturatoAnnualeMassimo.isPresent()) {
			clienti = cls.findByFatturatoAnnualeLessThanEqual(fatturatoAnnualeMassimo.get(), pageable);
		} else if (dataInserimentoMinima.isPresent() && dataInserimentoMassima.isPresent()) {
			clienti = cls.findByDataInserimentoBetween(dataInserimentoMinima.get(),dataInserimentoMassima.get(), pageable);
		} else if (dataInserimentoMinima.isPresent()) {
			clienti = cls.findByDataInserimentoGreaterThanEqual(dataInserimentoMinima.get(), pageable);
		} else if (dataInserimentoMassima.isPresent()) {
			clienti = cls.findByDataInserimentoLessThanEqual(dataInserimentoMassima.get(), pageable);
		} else if (dataUltimoContattoMinima.isPresent() && dataUltimoContattoMassima.isPresent()) {
			clienti = cls.findByDataUltimoContattoBetween(dataUltimoContattoMinima.get(),dataUltimoContattoMassima.get(), pageable);
		} else if (dataUltimoContattoMinima.isPresent()) {
			clienti = cls.findByDataUltimoContattoGreaterThanEqual(dataUltimoContattoMinima.get(), pageable);
		} else if (dataUltimoContattoMassima.isPresent()) {
			clienti = cls.findByDataUltimoContattoLessThanEqual(dataUltimoContattoMassima.get(), pageable);
		}
		else {
			clienti = cls.findAll(pageable);
		}

		Page<ClienteDTO> clientiDto = clienti.map(ClienteDTO::fromCliente);
		return new ResponseEntity<>(clientiDto, HttpStatus.OK);
	}

	@GetMapping("/ordina2")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Page<ClienteDTO>> listaClienteOrdinata2(Pageable pageable) {
		return pager(pageable);
	}
	
}
