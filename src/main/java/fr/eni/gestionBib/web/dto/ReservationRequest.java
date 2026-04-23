package fr.eni.gestionBib.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    // 📌 obligatoire : livre à réserver
    private Long bookId;

}