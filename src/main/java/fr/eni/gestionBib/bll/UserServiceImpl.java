package fr.eni.gestionBib.bll;

import org.springframework.stereotype.Service;

import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.dal.UserRepository;
import lombok.AllArgsConstructor;

//Permet de faire injecter la couche DAL associée
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public UserInfo getUserByEmail(String email) {
		 return userRepository.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public UserInfo updateUserByEmail(String email, UserInfo updatedUser) {
		  UserInfo user = userRepository.findByEmail(email)
		            .orElseThrow(() -> new RuntimeException("User not found"));

		    user.setNom(updatedUser.getNom());
		    user.setPrenom(updatedUser.getPrenom());
		    user.setEmail(updatedUser.getEmail());

		    return userRepository.save(user);
	}

}
