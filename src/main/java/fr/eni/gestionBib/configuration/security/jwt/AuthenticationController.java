package fr.eni.gestionBib.configuration.security.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.dal.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	private final AuthenticationService authenticationService; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService; 
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthenticationRequest request) {

	    UserInfo user = new UserInfo();
	    user.setPseudo(request.getPseudo());
	    user.setPassword(passwordEncoder.encode(request.getPassword())); 
	    user.setAuthority("USER");

	    userRepository.save(user);

	    return ResponseEntity.ok("User created");
	}
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
		}
	
	
  

}
