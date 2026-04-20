package fr.eni.gestionBib.bo;


import lombok.*;

import java.io.Serializable;

import jakarta.persistence.*;

import fr.eni.gestionBib.bo.Favorite;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   /* @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;*/

    
}