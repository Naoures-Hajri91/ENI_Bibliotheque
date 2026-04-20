package fr.eni.gestionBib.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	
	
}