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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private LocalDateTime publishedAt;

    @Column(nullable = false)
    private boolean reported;

   /* @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    @NotNull
    @ManyToOne
    private Book book;*/
}