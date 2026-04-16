package fr.eni.gestionBib.configuration.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	    private String nom;
	    private String prenom;
	    private String email;
	    private String password;

}
