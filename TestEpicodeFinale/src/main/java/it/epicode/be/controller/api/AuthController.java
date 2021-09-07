package it.epicode.be.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.be.dto.UtenteRegistrDTO;
import it.epicode.be.model.Utente;
import it.epicode.be.security.JwtUtils;
import it.epicode.be.security.UserDetailsImpl;
import it.epicode.be.security.login.LoginRequest;
import it.epicode.be.security.login.LoginResponse;
import it.epicode.be.service.UtenteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UtenteService uts;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}
	
	@PostMapping("/registrazione")
	public ResponseEntity<?> registerUser(@RequestBody UtenteRegistrDTO ur){
		
		String plainPassword = ur.getPassword();
		ur.setPassword(encoder.encode(plainPassword));
		
		Utente u = ur.toUtente();
		
		uts.save(u);
		
		log.info("email "+ u.getEmail());
		log.info("nome "+ u.getName());
		log.info("username "+ u.getUsername());
		log.info("password "+ u.getPassword());
				
		
		return ResponseEntity.ok("Salvataggio utente avvenuto con successo: " + u.getUsername());                                                                                                                                                                               		
	}
	
}
