package fr.eni.gestionBib.dal;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{
	
	   boolean existsByEmail(String email);

	   Optional<UserInfo> findByEmail(String email);
}
