package fr.eni.gestionBib.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import fr.eni.gestionBib.bll.ReservationService;
import fr.eni.gestionBib.bo.Reservation;
import fr.eni.gestionBib.bo.UserInfo;

@RestController
@RequestMapping("api/reservations")
@CrossOrigin
public class ReservationController {
	private final ReservationService reservationService;
	
	 public ReservationController(ReservationService reservationService) {
		 this.reservationService= reservationService;
	 }
	
	 
	 @GetMapping("/my")
		public List<Reservation> mesreservations(Authentication authentication) {

		    String email = (String) authentication.getPrincipal();
		    
		 
		    return reservationService.getReservationByEmailUser( email);
		}
	 
	   /*  @PostMapping
		 public  Reservation createReservation(@RequestParam(required = false,name="bookId") Long bookId,@RequestParam(required = false,name="userId") Long userId){
		
		return  reservationService.createReservation(bookId, userId);*/
		
	 @PostMapping("/{bookId}")
		public Reservation reserve(@PathVariable(name="bookId") Long bookId,
		                           Authentication authentication) {

		    String email = (String) authentication.getPrincipal();
		    
		 

		    return reservationService.createReservationByEmailUser(bookId, email);
		}
		
	}


