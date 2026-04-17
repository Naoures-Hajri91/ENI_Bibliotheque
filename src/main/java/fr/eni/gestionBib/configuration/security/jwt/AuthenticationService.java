package fr.eni.gestionBib.configuration.security.jwt;

import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.gestionBib.bo.Role;
import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.dal.RoleRepository;
import fr.eni.gestionBib.dal.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;

	// ================= LOGIN =================
	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		UserInfo user = userRepository	.findByEmail(request.getEmail())
										.orElseThrow(() -> new RuntimeException("User not found"));

		String jwtToken = jwtService.generateToken(user);

		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(jwtToken);

		return response;
	}

	// ================= REGISTER =================
	public UserInfo register(RegisterRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already used");
		}

		Role role = roleRepository.findByName("ADHERENT").orElseThrow(() -> new RuntimeException("Role not found"));

		UserInfo user = new UserInfo();
		user.setNom(request.getNom());
		user.setPrenom(request.getPrenom());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(role);

		return userRepository.save(user);
	}

	//
	public UserInfo getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}
}