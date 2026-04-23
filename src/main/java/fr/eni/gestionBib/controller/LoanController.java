package fr.eni.gestionBib.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.gestionBib.bll.LoanService;
import fr.eni.gestionBib.bo.Loan;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/loans")
@AllArgsConstructor
@CrossOrigin
public class LoanController {
	LoanService loanService;
	
	// 📌 Emprunter un livre
	@PostMapping("/borrow/{bookId}")
	public ResponseEntity<?> borrow(@PathVariable("bookId") Long bookId,
	                                Principal principal) {

	    String email = principal.getName();

	    Loan loan = loanService.borrowBook(bookId, email);

	    return ResponseEntity.ok(Map.of(
	            "message", "Livre emprunté avec succès",
	            "dueDate", loan.getDueDate(),
	            "status", loan.getStatus()
	    ));
	}
	
	
	@GetMapping("/my")
	public ResponseEntity<?> getMyLoans(Principal principal) {

	    if (principal == null) {
	        return ResponseEntity.status(401).body("Non authentifié");
	    }

	    String email = principal.getName();

	    return ResponseEntity.ok(
	            loanService.getMyLoans(email)
	    );
	}

}
