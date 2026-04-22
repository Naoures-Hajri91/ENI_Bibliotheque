package fr.eni.gestionBib.bo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import fr.eni.gestionBib.bo.enumeration.ReservationStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    @Column(nullable = false)
    private int rankInLine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationStatus status;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    
    
    
    // ⭐ GOOD PRACTICE : auto set date avant persist
    @PrePersist
    public void prePersist() {
        this.reservationDate = LocalDateTime.now();
    }
}