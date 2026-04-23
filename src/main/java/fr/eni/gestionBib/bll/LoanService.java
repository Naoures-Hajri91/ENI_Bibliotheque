package fr.eni.gestionBib.bll;

import java.util.List;

import fr.eni.gestionBib.bo.Loan;
import fr.eni.gestionBib.web.dto.LoanDTO;

public interface LoanService {
	Loan borrowBook(Long bookId, String email);
	
	List<LoanDTO> getMyLoans(String email);
}
