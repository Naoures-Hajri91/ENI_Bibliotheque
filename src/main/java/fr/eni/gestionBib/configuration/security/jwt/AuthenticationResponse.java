package fr.eni.gestionBib.configuration.security.jwt;

import lombok.*; 

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@EqualsAndHashCode 
public class AuthenticationResponse {
	private String token; 
}
