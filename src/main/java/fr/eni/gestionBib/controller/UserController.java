package fr.eni.gestionBib.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.gestionBib.bll.UserService;
import fr.eni.gestionBib.bo.UserInfo;
import lombok.AllArgsConstructor;

@AllArgsConstructor  //créer un constructeur

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
@RequestMapping("/api/user")
public class UserController {
	 private final UserService userService;
	 
	 @GetMapping("/me")
	 public UserInfo getCurrentUser(Authentication authentication) {
	     String email = authentication.getName();
	     return userService.getUserByEmail(email);
	 }
	 
	 @PutMapping("/me")
	 public UserInfo updateCurrentUser(Authentication authentication,
	                                   @RequestBody UserInfo user) {

	     String email = authentication.getName();
	     return userService.updateUserByEmail(email, user);
	 }
}