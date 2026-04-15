package fr.eni.gestionBib.bo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 

@Entity 
@Table(name="users")
public class UserInfo implements UserDetails {
	@Id 
	@Column(length = 250)
	private String pseudo; 
	
	@Column(length = 68, nullable = false) 
	private String password; 
	
	@Column(length = 15, nullable = false)
	private String authority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(authority));
	}

	@Override
	public String getUsername() {
		return pseudo;
	}

}
