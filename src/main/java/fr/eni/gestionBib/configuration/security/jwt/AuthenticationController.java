package fr.eni.gestionBib.configuration.security.jwt;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.gestionBib.bo.UserInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	// ================= REGISTER =================
	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

		authenticationService.register(request);

		return ResponseEntity.ok(Map.of("message", "User registered successfully"));
	}

	// ================= LOGIN =================
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}

	//
	@GetMapping("/me")
	public UserInfo getCurrentUser(Authentication authentication) {
		String email = authentication.getName();
		return authenticationService.getUserByEmail(email);
	}
}