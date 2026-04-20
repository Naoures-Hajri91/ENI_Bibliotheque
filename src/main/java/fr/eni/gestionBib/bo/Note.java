package fr.eni.gestionBib.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int stars;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime ratingDate;

  /*@NotNull
    @ManyToOne
    private UserInfo user;

    @NotNull
    @ManyToOne
    private Book book;*/
}