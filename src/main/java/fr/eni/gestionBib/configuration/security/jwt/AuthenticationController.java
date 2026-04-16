package fr.eni.gestionBib.configuration.security.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.gestionBib.bo.Role;
import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.dal.RoleRepository;
import fr.eni.gestionBib.dal.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	private final AuthenticationService authenticationService; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService; 
	}
	
	 @PostMapping("/register")
	    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

	        // 🔴 vérifier email unique
	        if (userRepository.existsByEmail(request.getEmail())) {
	            return ResponseEntity.badRequest().body("Email already used");
	        }

	        //  récupérer rôle par défaut
	        Role role = roleRepository.findByName("ADHERENT")
	                .orElseThrow(() -> new RuntimeException("Role not found"));

	        //  créer user
	        UserInfo user = new UserInfo();
	        user.setNom(request.getNom());
	        user.setPrenom(request.getPrenom());
	        user.setEmail(request.getEmail());
	       
	        //  BCrypt
	        user.setPassword(passwordEncoder.encode(request.getPassword()));

	        //  role
	        user.setRole(role);

	        userRepository.save(user);

	        return ResponseEntity.ok("User registered successfully");
	    }

	    // ================= LOGIN =================

	    @PostMapping("/login")
	    public ResponseEntity<AuthenticationResponse> login(
	            @RequestBody AuthenticationRequest request
	    ) {
	        return ResponseEntity.ok(
	                authenticationService.authenticate(request)
	        );
	    }
	
	
  

}
