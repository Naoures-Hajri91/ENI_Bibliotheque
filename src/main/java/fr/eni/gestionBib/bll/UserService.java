package fr.eni.gestionBib.bll;

import fr.eni.gestionBib.bo.UserInfo;

public interface UserService {
	 	UserInfo getUserByEmail(String email);

	    UserInfo updateUserByEmail(String email, UserInfo updatedUser);

}
