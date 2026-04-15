package fr.eni.gestionBib.configuration.security.jwt;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

}
