package fr.eni.gestionBib.configuration.security.jwt;

import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.dal.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        //  Vérification login/password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //  Récup user
        UserInfo user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  JWT
        String jwtToken = jwtService.generateToken(user);

        //  Response
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return response;
    }

}
