package fr.eni.gestionBib.bo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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
    private LocalDateTime loanDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LoanStatus status;

    /*@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @ManyToOne
    private Book book;*/
}