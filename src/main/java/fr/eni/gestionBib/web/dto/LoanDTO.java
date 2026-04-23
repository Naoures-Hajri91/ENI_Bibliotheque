package fr.eni.gestionBib.web.dto;

import java.time.LocalDateTime;

import fr.eni.gestionBib.bo.enumeration.LoanStatus;
import lombok.Data;


@Data
public class LoanDTO {
	
    private Integer id;
    private String bookTitle;
    private String author;
    private String coverUrl;

    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    private LoanStatus status;

}
