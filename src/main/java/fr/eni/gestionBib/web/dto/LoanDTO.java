package fr.eni.gestionBib.web.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.eni.gestionBib.bo.enumeration.LoanStatus;
import lombok.Data;


@Data
public class LoanDTO {
	
    private Integer id;
    private String bookTitle;
    private String author;
    private String coverUrl;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime loanDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime returnDate;

    private LoanStatus status;

}
