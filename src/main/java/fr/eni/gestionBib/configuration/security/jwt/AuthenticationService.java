package fr.eni.gestionBib.configuration.security.jwt;

import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import fr.eni.gestionBib.bo.UserInfo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service 
public class AuthenticationService {
	private UserInfoRepository userInfoRepository;
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getPseudo(), request.getPassword()));
		UserInfo user = userInfoRepository.findById(request.getPseudo()).orElseThrow(); 
		String jwtToken = jwtService.generateToken(user);
		AuthenticationResponse authResponse = new AuthenticationResponse(); 
		authResponse.setToken(jwtToken);
	return authResponse; 
	} 
}
