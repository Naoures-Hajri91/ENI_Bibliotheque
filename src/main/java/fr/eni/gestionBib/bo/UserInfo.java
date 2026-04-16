package fr.eni.gestionBib.bo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotBlank
	    private String nom;

	    @NotBlank
	    private String prenom;

	    @Email
	    @Column(unique = true, nullable = false)
	    private String email;

	    @NotBlank
	    @Column(nullable = false)
	    private String password;

	  

	    // ================= ROLE =================

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "role_id")
	    private Role role;

	    // ================= SPRING SECURITY =================

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return List.of(
	                new SimpleGrantedAuthority("ROLE_" + role.getName())
	        );
	    }

	    @Override
	    public String getUsername() {
	        return email; // login avec email
	    }

	    @Override
	    public String getPassword() {
	        return password;
	    }

	    // ================= FLAGS =================

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }
}
