package fr.eni.gestionBib.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.gestionBib.bll.ReservationService;
import fr.eni.gestionBib.bo.Reservation;

@RestController
@RequestMapping("api/reservations")
@CrossOrigin
public class ReservationController {
	private final ReservationService reservationService;
	
	 public ReservationController(ReservationService reservationService) {
		 this.reservationService= reservationService;
	 }
	
	 
	 
	     @PostMapping
		 public  Reservation createReservation(@RequestParam(required = false,name="bookId") Long bookId,@RequestParam(required = false,name="userId") Long userId){
		
		return  reservationService.createReservation(bookId, userId);
	}

}
