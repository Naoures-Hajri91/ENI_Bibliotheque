package fr.eni.gestionBib.bo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.eni.gestionBib.bo.enumeration.LoanStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime loanDate; //date d’emprunt

    @Column(nullable = false)
    private LocalDateTime dueDate;//date limite

    @Column
    private LocalDateTime returnDate;//date réelle de retour

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LoanStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"loans"})
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnoreProperties({"loans"})
    private Book book;
}