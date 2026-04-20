package fr.eni.gestionBib.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "Titre obligatoire")
    @Size(min = 2, max = 255)
    private String title;

    @NotBlank(message = "Auteur obligatoire")
    private String author;

    @NotBlank(message = "ISBN obligatoire")
    private String isbn;

    @Size(max = 1000)
    private String description;

    @NotBlank(message = "Catégorie obligatoire")
    private String category;

    @Min(value = 0, message = "totalCopies >= 0")
    private int totalCopies;

    @Min(value = 0, message = "availableCopies >= 0")
    private int availableCopies;

   
    private String coverUrl;

    @Min(0)
    @Max(5)
    private float avgRating;
}
