package fr.eni.gestionBib.dal;

import org.springframework.data.jpa.repository.JpaRepository;


import fr.eni.gestionBib.bo.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String>{

}
