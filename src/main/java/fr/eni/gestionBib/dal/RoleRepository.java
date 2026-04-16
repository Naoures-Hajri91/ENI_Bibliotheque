package fr.eni.gestionBib.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	   //  chercher un rôle par son nom
       Optional<Role> findByName(String name);

}
