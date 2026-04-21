package fr.eni.gestionBib.bo;
import jakarta.persistence.*;
import lombok.*;


import jakarta.validation.constraints.*;


import java.io.Serializable;

import fr.eni.gestionBib.bo.enumeration.Category;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titre obligatoire")
    @Size(min = 2, max = 255)
    @Column(nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Auteur obligatoire")
    @Size(min = 2, max = 255)
    @Column(nullable = false, length = 255)
    private String author;

    @NotBlank(message = "ISBN obligatoire")
    @Size(min = 10, max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Size(max = 2000)
    @Column(length = 2000)
    private String description;

    
    private String coverUrl;

    @NotNull
    @Min(value = 0, message = "totalCopies >= 0")
    @Column(nullable = false)
    private Integer totalCopies;

    @NotNull
    @Min(value = 0, message = "availableCopies >= 0")
    @Column(nullable = false)
    private Integer availableCopies;

    @Min(0)
    @Max(5)
    private float avgRating;
    
    @Enumerated(EnumType.STRING)
	private Category category;


   /* @PastOrPresent
    private LocalDateTime addedAt;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans;

    @OneToMany(mappedBy = "book")
    private List<Reservation> reservations;*/
}