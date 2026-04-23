package fr.eni.gestionBib.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Loan;
import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.bo.enumeration.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	long countByUserAndStatus(UserInfo user, LoanStatus status);

    boolean existsByUserAndStatus(UserInfo user, LoanStatus status);
    
    List<Loan> findByUser(UserInfo user);
	
}